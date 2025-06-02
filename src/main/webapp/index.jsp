<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickBites - Sign Up</title>
    <link rel="stylesheet" href="index.css">
    <style>
        

        /* Modal styles */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
            z-index: 1000;
        }

        .modal-content {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            text-align: center;
            max-width: 400px;
            width: 90%;
        }

        .modal-btn {
            padding: 10px 20px;
            background: #81c784;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 15px;
        }
    </style>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <div class="signup-container">
        <h1>Sign Up</h1>
        <form id="signupForm" action="UserRegister" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <div class="password-container">
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                    <span class="toggle-password" onclick="togglePassword('password', this)">
                        <i class="far fa-eye"></i>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <label for="confirmpassword">Confirm Password</label>
                <div class="password-container">
                    <input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm your password" required>
                    <span class="toggle-password" onclick="togglePassword('confirmpassword', this)">
                        <i class="far fa-eye"></i>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <textarea id="address" name="address" placeholder="Enter your address"></textarea>
            </div>
            <button type="submit" class="submit-btn">Register</button>
        </form>
        <div class="already-registered">
            <p>Already have an account? <a href="UserLogin.jsp">Log in</a></p>
        </div>
        <div class="social-login">
            <p>Or sign up with:</p>
            <div class="social-icons">
                <a href="#" class="social-icon"><i class="fab fa-facebook-f"></i></a>
                <a href="#" class="social-icon"><i class="fab fa-twitter"></i></a>
                <a href="#" class="social-icon"><i class="fab fa-google"></i></a>
                <a href="#" class="social-icon"><i class="fab fa-linkedin-in"></i></a>
            </div>
        </div>
    </div>

    <!-- Modals -->
    
    <div class="modal" id="confirmationModal">
        <div class="modal-content">
            <h2>Registration Complete!</h2>
            <p>Your account has been created successfully.</p>
            <button class="modal-btn" onclick="redirectToLogin()">Go to Login</button>
        </div>
    </div>

    <div class="modal" id="errorModal">
        <div class="modal-content">
            <h2>Registration Failed!</h2>
            <p>There was an issue with your registration. Please try again.</p>
            <button class="modal-btn" onclick="closeModal()">Close</button>
        </div>
    </div>

    <div class="modal" id="emailExistsModal">
        <div class="modal-content">
            <h2>Email Already Exists!</h2>
            <p>The email you entered is already registered.</p>
            <button class="modal-btn" onclick="closeModal()">Close</button>
        </div>
    </div>

    <div class="modal" id="passwordMismatchModal">
        <div class="modal-content">
            <h2>Passwords Do Not Match!</h2>
            <p>Please make sure both passwords are the same.</p>
            <button class="modal-btn" onclick="closeModal()">Close</button>
        </div>
    </div>

     <script>
        // Function to toggle password visibility
        function togglePassword(inputId, icon) {
            const passwordField = document.getElementById(inputId);
            const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordField.setAttribute('type', type);
            icon.innerHTML = type === 'password' ? '<i class="far fa-eye"></i>' : '<i class="far fa-eye-slash"></i>';
        }

        // Function to show a specific modal
        function showModal(modalId) {
            closeAllModals();
            const modal = document.getElementById(modalId);
            if (modal) {
                modal.style.display = 'flex';
            }
        }

        // Function to close all modals
        function closeAllModals() {
            const modals = document.getElementsByClassName('modal');
            for (let modal of modals) {
                modal.style.display = 'none';
            }
        }

        // Function to close the current modal
        function closeModal() {
            closeAllModals();
        }

        // Function to redirect to login page
        function redirectToLogin() {
            window.location.href = 'UserLogin.jsp';
        }

        // Function to validate the form before submission
        function validateForm(event) {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmpassword').value;

            if (password !== confirmPassword) {
                event.preventDefault();
                showModal('passwordMismatchModal');
                return false;
            }
            return true;
        }

        // Add form submission validation
        document.getElementById('signupForm').addEventListener('submit', validateForm);

        // Check for server response on page load
        window.onload = function() {
            <%
            // Check session first
            String sessionStatus = (String) session.getAttribute("status");
            if (sessionStatus != null) {
                // Clear the status from session after reading
                session.removeAttribute("status");
            }
            // If no session status, check request
            String requestStatus = (String) request.getAttribute("status");
            String status = sessionStatus != null ? sessionStatus : requestStatus;
            %>
            
            var status = '<%= status != null ? status : "" %>';
            
            if (status) {
                switch(status) {
                    case 'success':
                        showModal('confirmationModal');
                        break;
                    case 'failure':
                        showModal('errorModal');
                        break;
                    case 'emailExists':
                        showModal('emailExistsModal');
                        break;
                    case 'passwordMismatch':
                        showModal('passwordMismatchModal');
                        break;
                }
            }
        };
    </script>
</body>
</html>

