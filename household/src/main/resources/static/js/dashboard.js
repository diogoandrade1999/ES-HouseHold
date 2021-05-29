$(document).ready(function () {
    var temperatureChart;
    setTimeout(function () {
        $("#temperatureChartContainer").html(
            '<canvas id="temperatureChart"></canvas>'
        );
        temperatureChart = new Chart(
            document.getElementById("temperatureChart"),
            line_chart("Temperature")
        );
    }, 4500);

    setInterval(function () {
        let date = new Date();
        let endDate = parseInt((date.getTime() / 1000).toFixed(0));
        date.setDate(date.getDate() - 1);
        let startDate = parseInt((date.getTime() / 1000).toFixed(0));
        $.ajax({
            url:
                "http://localhost:51020/temperature/" +
                startDate +
                "/" +
                endDate,
            method: "GET",
            dataType: "json",
        })
            .done((res) => {
                var labelTime = [];
                var dataTemperature = [];
                var thresholdCold = [];
                var thresholdHot = [];
                res.slice(-50).forEach((element) => {
                    let dateElement = new Date(element.date);
                    labelTime.push(
                        addZero(dateElement.getHours()).toString() +
                            ":" +
                            addZero(dateElement.getMinutes()).toString() +
                            ":" +
                            addZero(dateElement.getSeconds()).toString()
                    );
                    dataTemperature.push(element.temperature);
                    thresholdCold.push(15);
                    thresholdHot.push(25);
                });
                temperatureChart.data.labels = labelTime;
                temperatureChart.data.datasets[0].data = dataTemperature;
                temperatureChart.data.datasets[1].data = thresholdCold;
                temperatureChart.data.datasets[2].data = thresholdHot;
                temperatureChart.update();
                if (dataTemperature.slice(-1) > 25) {
                    warning(
                        "High Temperature!",
                        "warningTemperature",
                        "danger"
                    );
                } else if (dataTemperature.slice(-1) < 15) {
                    warning(
                        "Low Temperature!",
                        "warningTemperature",
                        "primary"
                    );
                }
            })
            .fail((err) => {
                console.log("Fail request to Temperature API!");
            });
    }, 5000);
});
