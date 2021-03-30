let matched = window.matchMedia('(prefers-color-scheme: dark)').matches;
let element = document.body;
let iconToggler = document.querySelectorAll(".night-toggle i");

function toggleDarkMode(dark) {
    if (dark == null) {
        dark = !element.classList.contains("dark");
    }

    if (dark == true) {
        element.classList.add("dark");
        for (var i = 0; i < iconToggler.length; i++) {
            iconToggler[i].classList.remove("fa-sun-o");
            iconToggler[i].classList.add("fa-moon-o");
        }
    } else {
        element.classList.remove("dark");
        for (var i = 0; i < iconToggler.length; i++) {
            iconToggler[i].classList.add("fa-sun-o");
            iconToggler[i].classList.remove("fa-moon-o");
        }
    }
    localStorage.setItem("nightMode", dark);
}

if (matched && localStorage.getItem("nightMode") != 'false') {
    toggleDarkMode(true);
} else if (!matched && localStorage.getItem("nightMode") == 'true') {
    toggleDarkMode(true);
}
