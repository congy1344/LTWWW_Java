<%--
  Created by IntelliJ IDEA.
  User: CongY
  Date: 9/8/2025
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:useBean id="cart" scope="session" class="fit.se.ex4cart.model.CartBean" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }

        .container {
            width: 90%;
            max-width: 1000px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 30px;
        }

        h1 {
            color: #333;
            text-align: center;
            border-bottom: 3px solid #4a90e2;
            padding-bottom: 15px;
            margin-bottom: 30px;
            font-size: 2.2em;
        }

        .header-links {
            text-align: left;
            margin-bottom: 30px;
        }

        .header-links a {
            text-decoration: none;
            color: #4a90e2;
            font-weight: bold;
            padding: 10px 20px;
            background-color: #f0f8ff;
            border-radius: 5px;
            border: 2px solid #4a90e2;
            transition: all 0.2s;
        }

        .header-links a:hover {
            background-color: #4a90e2;
            color: white;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        th {
            background-color: #4a90e2;
            color: white;
            padding: 15px;
            font-weight: bold;
            text-align: left;
        }

        td {
            padding: 15px;
            border-bottom: 1px solid #eee;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f0f8ff;
        }

        .empty-cart {
            text-align: center;
            padding: 40px;
            color: #666;
            font-style: italic;
        }

        .product-info {
            display: flex;
            flex-direction: column;
        }

        .product-name {
            font-weight: bold;
            color: #333;
            margin-bottom: 5px;
        }

        .product-description {
            color: #666;
            font-size: 0.9em;
        }

        .quantity-controls {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .quantity-input {
            width: 50px;
            text-align: center;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        .action-btn {
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 12px;
            font-weight: bold;
            transition: all 0.2s;
        }

        .update-btn {
            background-color: #28a745;
            color: white;
        }

        .update-btn:hover {
            background-color: #218838;
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
        }

        .delete-btn:hover {
            background-color: #c82333;
        }

        .price-cell {
            font-weight: bold;
            color: #333;
        }

        .total-row {
            background-color: #f8f9fa !important;
            border-top: 2px solid #4a90e2;
        }

        .total-row td {
            font-size: 1.1em;
            font-weight: bold;
            padding: 20px 15px;
        }

        .total-label {
            text-align: right;
            color: #333;
        }

        .total-amount {
            color: #e74c3c;
            font-size: 1.3em;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            table {
                font-size: 14px;
            }

            th, td {
                padding: 10px 8px;
            }

            .quantity-controls {
                flex-direction: column;
                gap: 5px;
            }

            .action-btn {
                padding: 6px 12px;
                font-size: 11px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Shopping Cart</h1>
    <div class="header-links">
        <a href="products">&laquo; Product List</a>
    </div>

    <table>
        <thead>
        <tr>
            <th>Product Details</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${cart.lineItemCount == 0}">
            <tr>
                <td colspan="4" class="empty-cart">
                    Your cart is currently empty.<br>
                    <a href="products" style="color: #4a90e2;">Continue shopping</a>
                </td>
            </tr>
        </c:if>

        <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="counter">
            <tr>
                <td>
                    <div class="product-info">
                        <div class="product-name">${cartItem.strPartNumber}</div>
                        <div class="product-description">${cartItem.strModelDescription}</div>
                    </div>
                </td>
                <td>
                    <form method="POST" action="CartController">
                        <div class="quantity-controls">
                            <input type="text" class="quantity-input" name="quantity" value="${cartItem.iQuantity}">
                            <input type="hidden" name="itemIndex" value="${counter.count}">
                            <input type="submit" name="action" value="Update" class="action-btn update-btn">
                            <input type="submit" name="action" value="Delete" class="action-btn delete-btn">
                        </div>
                    </form>
                </td>
                <td class="price-cell">$${cartItem.dblUnitCost}</td>
                <td class="price-cell">$${cartItem.dblTotalCost}</td>
            </tr>
        </c:forEach>

        <c:if test="${cart.lineItemCount > 0}">
            <tr class="total-row">
                <td colspan="3" class="total-label">Subtotal:</td>
                <td class="total-amount">$${cart.orderTotal}</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
</body>
</html>