
const menuBtn = document.querySelector(".menu-btn");
const navigation = document.querySelector(".navigation");
const header = document.getElementsByTagName("header");
const image = document.getElementById("cartIcon");

const navLinks = document.querySelectorAll('header .navigation .navigation-items a');
const path = window.location.pathname.split('/');
const activePage = path[2];
const home = document.getElementById("homeurl").getAttribute("href").split('/');
const homeurl = home[0];
const fish = document.getElementById("fishurl").getAttribute("href").split('/');
const fishurl = fish[0];
const accessories = document.getElementById("accessoriesurl").getAttribute("href").split('/');
const accessoriesurl = accessories[0];
const about = document.getElementById("abouturl").getAttribute("href").split('/');
const abouturl = about[0];
const login = document.getElementById("loginurl").getAttribute("href").split('/');
const loginurl = login[0];
menuBtn.addEventListener("click", () => {
	menuBtn.classList.toggle("active");
	navigation.classList.toggle("active");

});
if (activePage === homeurl) {
	document.getElementById("homeurl").style.color = "#FFCC00";
} else if (activePage === fishurl) {
	document.getElementById("fishurl").style.color = "#FFCC00";
}
else if (activePage === "FishServlet") {
	document.getElementById("fishurl").style.color = "#FFCC00";
}
else if (activePage === accessoriesurl) {
	document.getElementById("accessoriesurl").style.color = "#FFCC00";
}
else if (activePage === "AccessoriesServlet") {
	document.getElementById("accessoriesurl").style.color = "#FFCC00";
}
else if (activePage === abouturl) {
	document.getElementById("abouturl").style.color = "#FFCC00";
} else if (activePage === loginurl) {
	document.getElementById("loginurl").style.color = "#FFCC00";
}
else if (activePage === "LoginServlet") {
	document.getElementById("loginurl").style.color = "#FFCC00";
}
else if (activePage === "register.jsp") {
	document.getElementById("loginurl").style.color = "#FFCC00";
}
else if (activePage === "RegisterServlet") {
	document.getElementById("loginurl").style.color = "#FFCC00";
}
window.addEventListener("scroll", function() {
	var header = document.querySelector(".header");
	if (window.scrollY > 0) {
		header.classList.add("nav-color");
		header.style.boxShadow = '0 0 30px 0px rgba(1, 9, 255, 0.5)';
		if (activePage === homeurl) {
			document.getElementById("homeurl").style.color = "#FFCC00";
			document.getElementById("fishurl").style.color = "#000";
			document.getElementById("accessoriesurl").style.color = "#000";
			document.getElementById("abouturl").style.color = "#000";
			document.getElementById("loginurl").style.color = "#000";

		} else if (activePage === fishurl || activePage === "FishServlet") {
			document.getElementById("homeurl").style.color = "#000";
			document.getElementById("fishurl").style.color = "#FFCC00";
			document.getElementById("accessoriesurl").style.color = "#000";
			document.getElementById("abouturl").style.color = "#000";
			document.getElementById("loginurl").style.color = "#000";

		}
		else if (activePage === accessoriesurl || activePage === "AccessoriesServlet") {
			document.getElementById("homeurl").style.color = "#000";
			document.getElementById("fishurl").style.color = "#000";
			document.getElementById("accessoriesurl").style.color = "#FFCC00";
			document.getElementById("abouturl").style.color = "#000";
			document.getElementById("loginurl").style.color = "#000";

		}
		else if (activePage === abouturl) {
			document.getElementById("homeurl").style.color = "#000";
			document.getElementById("fishurl").style.color = "#000";
			document.getElementById("accessoriesurl").style.color = "#000";
			document.getElementById("abouturl").style.color = "#FFCC00";
			document.getElementById("loginurl").style.color = "#000";

		} else if (activePage === loginurl || activePage == "LoginServlet" || activePage == "RegisterServlet" ||
			activePage == "register.jsp") {
			document.getElementById("homeurl").style.color = "#000";
			document.getElementById("fishurl").style.color = "#000";
			document.getElementById("accessoriesurl").style.color = "#000";
			document.getElementById("abouturl").style.color = "#000";
			document.getElementById("loginurl").style.color = "#FFCC00";

		}


	} else {


		header.classList.remove("nav-color");
		header.style.boxShadow = '';
		if (activePage === homeurl) {
			document.getElementById("homeurl").style.color = "#FFCC00";
			document.getElementById("fishurl").style.color = "white";
			document.getElementById("accessoriesurl").style.color = "white";
			document.getElementById("abouturl").style.color = "white";
			document.getElementById("loginurl").style.color = "white";

		} else if (activePage === fishurl || activePage == "FishServlet") {
			document.getElementById("homeurl").style.color = "white";
			document.getElementById("fishurl").style.color = "#FFCC00";
			document.getElementById("accessoriesurl").style.color = "white";
			document.getElementById("abouturl").style.color = "white";
			document.getElementById("loginurl").style.color = "white";

		}
		else if (activePage === accessoriesurl || activePage == "AccessorieServlet") {
			document.getElementById("homeurl").style.color = "white";
			document.getElementById("fishurl").style.color = "white";
			document.getElementById("accessoriesurl").style.color = "#FFCC00";
			document.getElementById("abouturl").style.color = "white";
			document.getElementById("loginurl").style.color = "white";

		}
		else if (activePage === abouturl) {
			document.getElementById("homeurl").style.color = "white";
			document.getElementById("fishurl").style.color = "white";
			document.getElementById("accessoriesurl").style.color = "white";
			document.getElementById("abouturl").style.color = "#FFCC00";
			document.getElementById("loginurl").style.color = "white";

		} else if (activePage === loginurl || activePage == "LoginServlet" || activePage == "RegisterServlet" ||
			activePage == "register.jsp") {
			document.getElementById("homeurl").style.color = "white";
			document.getElementById("fishurl").style.color = "white";
			document.getElementById("accessoriesurl").style.color = "white";
			document.getElementById("abouturl").style.color = "white";
			document.getElementById("loginurl").style.color = "#FFCC00";

		}
	}
});

