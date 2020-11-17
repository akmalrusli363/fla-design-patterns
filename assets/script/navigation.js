var navMenu = document.getElementById('navMenu');
var nav = document.getElementById('navigationBar');

navMenu.style.right = (navMenu.clientWidth - navMenu.offsetWidth) + "px";

if (window.pageYOffset == 0 && nav.classList.contains("blend")) {
  nav.classList.add("transparent");
}

function peekDropdown() {
  if (navMenu.className === "menu") {
    navMenu.classList.add("responsive");
    nav.classList.remove("transparent");
  } else {
    navMenu.classList.remove("responsive");
    if (window.pageYOffset == 0 && nav.classList.contains("blend"))
      nav.classList.add("transparent");
  }
}

window.onscroll = function () {
  if (window.pageYOffset == 0 && nav.classList.contains("blend")) {
    nav.classList.add("transparent");
  } else {
    nav.classList.remove("transparent");
  }
};
