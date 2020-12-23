let matched = window.matchMedia('(prefers-color-scheme: dark)').matches;
let element = document.body;
let iconToggler = document.querySelector(".night-toggle i");

function toggleDarkMode(dark) {
    if (dark == null) {
        dark = !element.classList.contains("dark");
    }

    if (dark == true) {
        element.classList.add("dark");
        iconToggler.classList.remove("fa-sun-o");
        iconToggler.classList.add("fa-moon-o");
    } else {
        element.classList.remove("dark");
        iconToggler.classList.add("fa-sun-o");
        iconToggler.classList.remove("fa-moon-o");
    }
    sessionStorage.setItem("nightMode", dark);
}

if (matched && sessionStorage.getItem("nightMode") != 'false') {
    toggleDarkMode(true);
} else if (!matched && sessionStorage.getItem("nightMode") == 'true') {
    toggleDarkMode(true);
}