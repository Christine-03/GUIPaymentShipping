<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Payment, model.BuyerDetail" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thank You for Your Order</title>
    <link href="../CSS/thankyou.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="tq-container">
        <div class="thank-you-box">
            <h1 class="title">THANK YOU FOR YOUR ORDER</h1>
            <p class="conclusion-text">We've received your order and are processing it...</p>
            
            <div class="info-section">
                <div class="info-row">
                    <span class="info-label">Order Number</span>
                    <span class="info-value">${order.orderId}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Order Date</span>
                    <span class="info-value">${orderDate}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Order Status</span>
                    <span class="info-value">Processing</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Payment Method</span>
                    <span class="info-value">${paymentMethod}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Order Total</span>
                    <span class="info-value">RM ${totalAmount}</span>
                </div>
            </div>
            
            <div class="delivery-info">
                <p><strong>Estimated delivery:</strong> 3-5 business days</p>
                <p><strong>Shipping address:</strong> ${shippingAddress}</p>
            </div>
            
            <div class="backHomeButton">
                <a href="UserHome.jsp" class="home-link">Return to Home</a>
            </div>
        </div>
    </div>
</body>
</html>


<!--<!DOCTYPE html>
<html>
<head>
    <title>Thank You</title>
    <style>
        .thank-you { text-align: center; margin-top: 50px; }
        .receipt { margin: 20px auto; padding: 20px; border: 1px solid #ddd; width: 60%; }
        a { color: #4CAF50; }
    </style>
</head>
<body>
    <div class="thank-you">
        <div class="receipt">
        <h1>Thank You for Your Order!</h1>
            <p>We've received your order and are processing it. You'll receive a confirmation email shortly.</p>
            <p>Your order ID: <strong>${payment.orderId}</strong></p>
            <p>A receipt has been sent to: <strong>${buyer.email}</strong></p>
            <p>Your order ID: ${orderId}</p>
            <p>A receipt has been sent to your email.</p> 
            
            <div class="order-details">
                <p>Estimated delivery: 3-5 business days</p>
            </div>
            
            <p><a href="UserHome.jsp">Return to Home</a></p>
        </div>
    </div>
</body>
</html>-->