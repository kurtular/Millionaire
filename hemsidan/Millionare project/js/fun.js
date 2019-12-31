const navSlide = () => {
    const burger = document.querySelector('.burger');
    const nav = document.querySelector('.nav-links');
    const navLinks = document.querySelectorAll('.nav-links li');

    burger.addEventListener('click', () => {
        //toggle the navi
        nav.classList.toggle('nav-active');

        // animate links
        navLinks.forEach((link, index) => {
            // set animation to empty if there is already animation
            if(link.style.animation){
                link.style.animation = '';
            // if there is no animation set animation
            }else{
                link.style.animation = `navLinkFade 0.5s ease forwards ${index / 5 + 0.3}s`;
            }
            /* delay between the links*/
        });
        // animate the navigationbar
        burger.classList.toggle('rotateLine');
    });
}
navSlide();

var slideIndex = 0;
function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) { slideIndex = 1 }
    slides[slideIndex - 1].style.display = "block";
    setTimeout(showSlides, 5000); // Change image every 2 seconds
}
showSlides();