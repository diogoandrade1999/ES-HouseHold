const line_chart = (labelName) => {
    var data = {
        labels: [],
        datasets: [
            {
                label: labelName,
                backgroundColor: "rgb(255, 99, 132)",
                borderColor: "rgb(255, 99, 132)",
                borderWidth: 2,
                radius: 0,
                data: [],
            },
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
                    text: labelName + " House",
                    size: 30,
                },
            },
        },
    };

    return config;
};
