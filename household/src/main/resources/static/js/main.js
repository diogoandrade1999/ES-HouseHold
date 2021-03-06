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

const capitalize = (s) => {
    if (typeof s !== "string") return "";
    return s.charAt(0).toUpperCase() + s.slice(1);
};

var color_codes = {};
const stringToColorCode = (str) => {
    return str in color_codes
        ? color_codes[str]
        : (color_codes[str] =
              "#" +
              ("000000" + ((Math.random() * 0xffffff) << 0).toString(16)).slice(
                  -6
              ));
};
