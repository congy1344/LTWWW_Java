<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }

        .container {
            width: 90%;
            max-width: 1200px;
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
            text-align: right;
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

        .product-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 25px;
        }

        .product-card {
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px;
            text-align: center;
            background-color: #fafafa;
            transition: box-shadow 0.2s;
        }

        .product-card:hover {
            box-shadow: 0 4px 12px rgba(0,0,0,0.15);
        }

        .product-card img {
            width: 150px;
            height: 150px;
            object-fit: contain;
            margin-bottom: 15px;
        }

        .product-name {
            font-size: 1.1em;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }

        .product-price {
            color: #e74c3c;
            font-size: 1.3em;
            font-weight: bold;
            margin-bottom: 15px;
        }

        .product-form {
            display: flex;
            flex-direction: column;
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

        .add-btn {
            background-color: #4a90e2;
            color: white;
            border: none;
            padding: 10px 25px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
            transition: background-color 0.2s;
        }

        .add-btn:hover {
            background-color: #357abd;
        }

        /* Responsive */
        @media (max-width: 768px) {
            .product-grid {
                grid-template-columns: repeat(2, 1fr);
                gap: 20px;
            }
        }

        @media (max-width: 480px) {
            .product-grid {
                grid-template-columns: 1fr;
            }
            .container {
                padding: 20px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Product List</h1>
    <div class="header-links">
        <a href="ShoppingCart.jsp">View Cart &raquo;</a>
    </div>

    <div class="product-grid">
        <c:forEach items="${ds}" var="sp">
            <div class="product-card">
                <img src="${sp.imgURL}" alt="${sp.model}"/>
                <div class="product-name">${sp.model}</div>
                <div class="product-price">$${sp.price}</div>

                <form class="product-form" method="POST" action="<c:url value="/CartController" />">
                    <input type="text" class="quantity-input" name="quantity" value="1" min="1"/>
                    <input type="hidden" name="modelNo" value="${sp.id}">
                    <input type="hidden" name="price" value="${sp.price}">
                    <input type="hidden" name="description" value="${sp.model}">
                    <input type="hidden" name="action" value="add">
                    <input type="submit" class="add-btn" value="Add To Cart">
                </form>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>