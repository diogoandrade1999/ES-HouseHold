const line_chart = (labelName) => {
    var data = {
        labels: [],
        datasets: [
            {
                label: "Lower Threshold",
                backgroundColor: "rgb(0, 191, 255)",
                borderColor: "rgb(0, 191, 255)",
                borderWidth: 1,
                radius: 0,
                data: [],
            },
            {
                label: "Upper Threshold",
                backgroundColor: "rgb(255, 69, 0)",
                borderColor: "rgb(255, 69, 0)",
                borderWidth: 1,
                radius: 0,
                data: [],
            },
        ],
    };

    var config = {
        type: "line",
        data,
        options: {
            animation: {
                duration: 0,
            },
            responsive: true,
            plugins: {
                legend: {
                    position: "top",
                },
                title: {
                    display: true,
                    text: labelName,
                    size: 30,
                },
            },
        },
    };

    return config;
};
