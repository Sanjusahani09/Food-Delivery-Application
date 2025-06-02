<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickBites - Login</title>
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

        .login-container {
            background: rgba(255, 255, 255, 0.9);
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            padding: 2rem;
            width: 100%;
            max-width: 400px;
        }

        .login-container h1 {
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

        .submit-btn {
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

        .submit-btn:hover {
            transform: translateY(-2px);
        }

        .remember-forgot {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            font-size: 0.9rem;
            margin-top: 0.5rem;
        }

        .remember-forgot a {
            color: #4ECDC4;
            text-decoration: none;
            font-weight: 600;
        }

        .remember-forgot a:hover {
            text-decoration: underline;
        }

        .not-registered {
            margin-top: 1.5rem;
            text-align: center;
            font-size: 0.9rem;
        }

        .not-registered a {
            color: #4ECDC4;
            text-decoration: none;
            font-weight: 600;
        }

        .not-registered a:hover {
            text-decoration: underline;
        }

        @media (max-width: 480px) {
            .login-container {
                padding: 1.5rem;
            }

            .login-container h1 {
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

            .submit-btn {
                font-size: 1rem;
                padding: 0.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h1>Sign In</h1>
        <form action="UserLogin" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <div class="password-container">
                    <input type="password" id="password" name="password" placeholder="Enter your password" required>
                    <span class="toggle-password" onclick="togglePassword()">
                        <i class="far fa-eye"></i>
                    </span>
                </div>
            </div>
            <div class="remember-forgot">
                <a href="forgotPassword.jsp">Forgot password?</a>
            </div>
            <button type="submit" class="submit-btn">Login</button>
        </form>
        <div class="not-registered">
            <p>Not registered yet? <a href="index.jsp">Register here</a></p>
        </div>
    </div>

    <script>
        function togglePassword() {
            const passwordField = document.getElementById("password");
            const toggleIcon = document.querySelector(".toggle-password i");

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
    </script>
</body>
</html>

