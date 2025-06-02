document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('checkoutForm');
    const paymentOptions = document.querySelectorAll('input[name="paymentMethod"]');
    const cardDetails = document.getElementById('cardDetails');
    const upiDetails = document.getElementById('upiDetails');
    const submitButton = document.getElementById('submitButton');
    const deliveryAnimation = document.querySelector('.delivery-animation');
    const orderSuccess = document.querySelector('.order-success');
    const overlay = document.querySelector('.overlay');

    function togglePaymentDetails() {
        const selectedPayment = document.querySelector('input[name="paymentMethod"]:checked').value;
        
        if (selectedPayment === 'card') {
            cardDetails.classList.remove('hidden');
            upiDetails.classList.add('hidden');
        } else if (selectedPayment === 'upi') {
            upiDetails.classList.remove('hidden');
            cardDetails.classList.add('hidden');
        } else {
            cardDetails.classList.add('hidden');
            upiDetails.classList.add('hidden');
        }
    }

    function playOrderAnimation() {
        // Start animation
        deliveryAnimation.classList.add('active');
        overlay.classList.add('active');

        // Show success message after animation
        setTimeout(() => {
            form.submit();
        }, 2000);
    }

    // Card number formatting
    const cardNumber = document.getElementById('cardNumber');
    if (cardNumber) {
        cardNumber.addEventListener('input', function(e) {
            let value = e.target.value.replace(/\D/g, '');
            value = value.replace(/(\d{4})/g, '$1 ').trim();
            e.target.value = value.substring(0, 19);
        });
    }

    // Expiry date formatting
    const cardExpiry = document.getElementById('cardExpiry');
    if (cardExpiry) {
        cardExpiry.addEventListener('input', function(e) {
            let value = e.target.value.replace(/\D/g, '');
            if (value.length >= 2) {
                value = value.substring(0, 2) + '/' + value.substring(2);
            }
            e.target.value = value.substring(0, 5);
        });
    }

    // CVV input restriction
    const cardCvv = document.getElementById('cardCvv');
    if (cardCvv) {
        cardCvv.addEventListener('input', function(e) {
            e.target.value = e.target.value.replace(/\D/g, '').substring(0, 3);
        });
    }

    // Add event listeners
    paymentOptions.forEach(option => {
        option.addEventListener('change', togglePaymentDetails);
    });

    

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        submitButton.disabled = true;
        submitButton.innerHTML = `
            <img src="https://cdn-icons-png.flaticon.com/512/4461/4461744.png" alt="Loading" class="button-icon loading">
            Processing...
        `;
        
        // Simulate form submission and play animation
        setTimeout(() => {
            playOrderAnimation();
        }, 3000);
    });

    // Initial toggle
    togglePaymentDetails();
});