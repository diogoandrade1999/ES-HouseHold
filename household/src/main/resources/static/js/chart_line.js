const line_chart = (labelTime, labelData, labelName) => {
    const data = {
        labels: labelTime,
        datasets: [
            {
                label: labelName,
                backgroundColor: "rgb(255, 99, 132)",
                borderColor: "rgb(255, 99, 132)",
                borderWidth: 1,
                radius: 0,
                data: labelData,
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
