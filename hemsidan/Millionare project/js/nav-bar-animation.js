const navSlide = () => {
    const burger = document.querySelector('.burger');
    const nav = document.querySelector('.nav-links');
    const navLinks = document.querySelectorAll('.nav-links li');

    burger.addEventListener('click', () => {
        //toggle the nav
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