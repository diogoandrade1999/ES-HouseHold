const addZero = (number) => {
    if (number <= 9) return "0" + number;
    return number;
};

const warning = (message, id, bg) => {
    $("#" + id).html(
        `<div class="toast align-items-center text-white bg-` +
            bg +
            ` border-0" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="d-flex">
                <div class="toast-body">` +
            message +
            `</div>
                <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>`
    );
    $(".toast").toast("show");
};
