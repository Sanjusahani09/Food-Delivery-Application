document.addEventListener('DOMContentLoaded', () => {
    const statusContainer = document.querySelector('.status-container');
    const steps = document.querySelectorAll('.status-step');
    const statusProgress = document.querySelector('.status-progress');
    const bikeContainer = document.querySelector('.bike-container');
    let currentStep = 0;

    function updateStatus(step) {
        const progress = (step / (steps.length - 1)) * 100;
        statusProgress.style.width = `${progress}%`;
        
        steps.forEach((stepElement, index) => {
            if (index <= step) {
                stepElement.classList.add('active');
            } else {
                stepElement.classList.remove('active');
            }
        });

        const containerWidth = statusContainer.offsetWidth;
        const bikeWidth = bikeContainer.offsetWidth;
        const maxLeft = containerWidth - bikeWidth;
        const newPosition = (maxLeft * progress) / 100;
        bikeContainer.style.left = `${newPosition}px`;
    }

    function updateOrderProgress() {
        if (currentStep < steps.length - 1) {
            currentStep++;
            updateStatus(currentStep);
            
            if (currentStep < steps.length - 1) {
                setTimeout(updateOrderProgress, 100000);
            }
        }
    }

    // Initialize
    updateStatus(currentStep);
    setTimeout(updateOrderProgress, 2000);
});