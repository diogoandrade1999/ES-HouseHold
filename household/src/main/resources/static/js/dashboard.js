$(document).ready(function () {
    var temperatureChart;
    var humidityChart;
    var luminosityChart;
    setTimeout(function () {
        $("#temperatureChartContainer").html(
            '<canvas id="temperatureChart"></canvas>'
        );
        temperatureChart = new Chart(
            document.getElementById("temperatureChart"),
            line_chart("Temperature")
        );
        $("#humidityChartContainer").html(
            '<canvas id="humidityChart"></canvas>'
        );
        humidityChart = new Chart(
            document.getElementById("humidityChart"),
            line_chart("Humidity")
        );
        $("#luminosityChartContainer").html(
            '<canvas id="luminosityChart"></canvas>'
        );
        luminosityChart = new Chart(
            document.getElementById("luminosityChart"),
            line_chart("Luminosity")
        );
    }, 4500);

    setInterval(function () {
        getDataRestApi(
            "http://localhost:51020/temperature",
            15,
            25,
            temperatureChart,
            "temperature"
        );
        getDataRestApi(
            "http://localhost:51030/luminosity",
            100,
            300,
            luminosityChart,
            "luminosity"
        );
        getDataRestApi(
            "http://localhost:51040/humidity",
            20,
            60,
            humidityChart,
            "humidity"
        );
    }, 5000);

    const getDataRestApi = (
        apiUrl,
        thresholdLowVal,
        thresholdUpVal,
        chartObject,
        labelName
    ) => {
        let date = new Date();
        let endDate = parseInt((date.getTime() / 1000).toFixed(0));
        date.setDate(date.getDate() - 1);
        let startDate = parseInt((date.getTime() / 1000).toFixed(0));

        $.ajax({
            url: apiUrl + "/" + startDate + "/" + endDate,
            method: "GET",
            dataType: "json",
        })
            .done((res) => {
                var labelTime = [];
                var dataApi = [];
                var thresholdLow = [];
                var thresholdUp = [];
                res.slice(-50).forEach((element) => {
                    let dateElement = new Date(element.date);
                    labelTime.push(
                        addZero(dateElement.getHours()).toString() +
                            ":" +
                            addZero(dateElement.getMinutes()).toString() +
                            ":" +
                            addZero(dateElement.getSeconds()).toString()
                    );
                    dataApi.push(element[labelName]);
                    thresholdLow.push(thresholdLowVal);
                    thresholdUp.push(thresholdUpVal);
                });
                chartObject.data.labels = labelTime;
                chartObject.data.datasets[0].data = dataApi;
                chartObject.data.datasets[1].data = thresholdLow;
                chartObject.data.datasets[2].data = thresholdUp;
                chartObject.update();
                var lName = capitalize(labelName);
                if (dataApi.slice(-1)[0] > thresholdUpVal) {
                    warning("High " + lName + "!", "warning" + lName, "danger");
                } else if (dataApi.slice(-1)[0] < thresholdLowVal) {
                    warning("Low " + lName + "!", "warning" + lName, "primary");
                }
            })
            .fail((err) => {
                console.log("Fail request from Rest API!");
            });
    };
});
