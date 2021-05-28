const line_chart = (labelTime, dataTemperature) => {
    const labels = labelTime;
    const data = {
        labels: labels,
        datasets: [
            {
                label: "Temperature",
                backgroundColor: "rgb(255, 99, 132)",
                borderColor: "rgb(255, 99, 132)",
                data: dataTemperature,
            },
        ],
    };

    const config = {
        type: "line",
        data,
        options: {},
    };

    return config;
};
