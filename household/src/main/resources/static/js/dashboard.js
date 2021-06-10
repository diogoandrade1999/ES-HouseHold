$(document).ready(function () {
    var houseId = $("#houseId").val();
    var roomId = $("#roomId").val();
    var endUrl = houseId;
    if (roomId != "") {
        endUrl += "/" + roomId;
    }
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
        getDataRestApi(15, 25, temperatureChart, "temperature");
        getDataRestApi(100, 300, luminosityChart, "luminosity");
        getDataRestApi(20, 60, humidityChart, "humidity");
        getAlertsRestApi();
    }, 5000);

    const getDataRestApi = (
        thresholdLowVal,
        thresholdUpVal,
        chartObject,
        apiName
    ) => {
        let date = new Date();
        let endDate = parseInt((date.getTime() / 1000).toFixed(0));
        date.setDate(date.getDate() - 1);
        let startDate = parseInt((date.getTime() / 1000).toFixed(0));
        $.ajax({
            url:
                "/api/" +
                apiName +
                "/" +
                startDate +
                "/" +
                endDate +
                "/" +
                endUrl,
            method: "GET",
            dataType: "json",
        })
            .done((res) => {
                var dataDate = {};
                var thresholdLow = [];
                var thresholdUp = [];
                var dataApi = {};
                res.slice(-50).forEach((element) => {
                    let dateElement = new Date(element.date);
                    let formatDate =
                        addZero(dateElement.getHours()).toString() +
                        ":" +
                        addZero(dateElement.getMinutes()).toString() +
                        ":" +
                        addZero(dateElement.getSeconds()).toString();
                    if (!(formatDate in dataDate)) {
                        dataDate[formatDate] = {};
                    }
                    dataDate[formatDate][element.roomId] = element[apiName];

                    if (!(element.roomId in dataApi)) {
                        dataApi[element.roomId] = [];
                    }
                    thresholdLow.push(thresholdLowVal);
                    thresholdUp.push(thresholdUpVal);
                });

                chartObject.data.labels = Object.keys(dataDate);
                chartObject.data.datasets[0].data = thresholdLow;
                chartObject.data.datasets[1].data = thresholdUp;
                if (Object.keys(chartObject.data.datasets).length > 2) {
                    for (
                        var i = 0;
                        i < Object.keys(chartObject.data.datasets).length;
                        i++
                    ) {
                        chartObject.data.datasets.splice(2, 1);
                    }
                }
                Object.keys(dataDate).forEach((date) => {
                    Object.keys(dataDate[date]).forEach((roomId) => {
                        dataApi[roomId].push(dataDate[date][roomId]);
                    });
                });
                Object.keys(dataApi).forEach((roomId) => {
                    chartObject.data.datasets.push({
                        label: "Room " + roomId,
                        backgroundColor: stringToColorCode(roomId),
                        borderColor: stringToColorCode(roomId),
                        borderWidth: 2,
                        radius: 0,
                        data: dataApi[roomId],
                    });
                });
                chartObject.update();

                // WARINING
                /*var lName = capitalize(apiName);
                if (dataApi.slice(-1)[0] > thresholdUpVal) {
                    warning("High " + lName + "!", "warning" + lName, "danger");
                } else if (dataApi.slice(-1)[0] < thresholdLowVal) {
                    warning("Low " + lName + "!", "warning" + lName, "primary");
                }*/
            })
            .fail((err) => {
                console.log("Fail request from Rest API!");
            });
    };

    const getAlertsRestApi = (
    ) => {
    $.ajax({
                url:"/houses/alerts/",
                method: "GET",
                dataType: "json",
            })
            .done((res) => {
                console.log(res);
            })
            .fail((err) => {
                console.log("Fail request from Alerts Rest API!");
            });
    };
});
