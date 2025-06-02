<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TasteWay - Forgot Password</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
                        url('https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=1600&auto=format&fit=crop&q=60') center/cover no-repeat fixed;
            color: #333;
            line-height: 1.4;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 1rem;
        }

        .forgot-container {
            background: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            padding: 2rem;
            width: 100%;
            max-width: 400px;
        }

        .forgot-container h1 {
            font-size: 2rem;
            margin-bottom: 1.5rem;
            text-align: center;
            color: #333;
            background: linear-gradient(45deg, #FF6B6B, #4ECDC4);
            -webkit-background-clip: text;
            background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .form-group {
            margin-bottom: 1.25rem;
        }

        .form-group label {
            display: block;
            font-size: 0.9rem;
            font-weight: 600;
            margin-bottom: 0.3rem;
            color: #444;
        }

        .form-group input {
            width: 100%;
            padding: 0.6rem;
            font-size: 1rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f8f9fa;
            transition: border-color 0.3s ease;
        }

        .form-group input:focus {
            border-color: #4ECDC4;
            outline: none;
        }

        .password-container {
            position: relative;
        }

        .toggle-password {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            color: #666;
            font-size: 0.9rem;
        }

        .update-btn {
            background: linear-gradient(45deg, #FF6B6B, #4ECDC4);
            color: white;
            font-size: 1.1rem;
            font-weight: 600;
            padding: 0.6rem;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            transition: transform 0.3s ease;
            margin-top: 1rem;
        }

        .update-btn:hover {
            transform: translateY(-2px);
        }

        .error {
            color: #FF6B6B;
            font-size: 0.9rem;
            margin-top: 0.5rem;
            text-align: center;
        }

        @media (max-width: 480px) {
            .forgot-container {
                padding: 1.5rem;
            }

            .forgot-container h1 {
                font-size: 1.8rem;
            }

            .form-group {
                margin-bottom: 1rem;
            }

            .form-group label {
                font-size: 0.85rem;
            }

            .form-group input {
                padding: 0.5rem;
                font-size: 0.95rem;
            }

            .update-btn {
                font-size: 1rem;
                padding: 0.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="forgot-container">
        <h1>Forgot Password</h1>
        <form action="ForgotPassword" method="post" onsubmit="return validatePasswords()">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <label for="newPassword">New Password</label>
                <div class="password-container">
                    <input type="password" id="newPassword" name="newPassword" placeholder="Enter new password" required>
                    <span class="toggle-password" onclick="togglePassword('newPassword')">
                        <i class="far fa-eye"></i>
                    </span>
                </div>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <div class="password-container">
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm new password" required>
                    <span class="toggle-password" onclick="togglePassword('confirmPassword')">
                        <i class="far fa-eye"></i>
                    </span>
                </div>
            </div>
            <div class="error" id="error-message"></div>
            <button type="submit" class="update-btn">Update Password</button>
        </form>
    </div>

    <script>
        function togglePassword(fieldId) {
            const passwordField = document.getElementById(fieldId);
            const toggleIcon = passwordField.nextElementSibling.querySelector('i');

            if (passwordField.type === "password") {
                passwordField.type = "text";
                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");
            } else {
                passwordField.type = "password";
                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");
            }
        }

        function validatePasswords() {
            const newPassword = document.getElementById('newPassword').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const errorMessage = document.getElementById('error-message');

            if (newPassword !== confirmPassword) {
                errorMessage.textContent = "Passwords do not match!";
                return false;
            }

            if (newPassword.length > 8) {
                errorMessage.textContent = "Password must be at least 8 characters long!";
                return false;
            }

            errorMessage.textContent = "";
            return true;
        }
    </script>
</body>
</html>

