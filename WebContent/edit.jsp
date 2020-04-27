<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/edit.css">
    <title>编辑产品</title>
</head>
<body>
    <br><br>
    <h3 align="center">编辑产品</h3>
    <form  action="ProductServlet" method="post">
        <input type="hidden" name="method" value="edit"/>
        <input type="hidden" name="id" value="${product.id}">
        <div id="edit">
            <input type="text" name="barcode" required="required" placeholder="商品编码" value="${product.barcode}"/>
            <input type="text" name="name" required="required" placeholder="商品名称" value="${product.name}"/>
            <input type="text" name="units" required="required" placeholder="计量单位" value="${product.units}"/>
            <input type="text" name="purchasePrice" required="required" placeholder="进价" value="${product.purchasePrice}"/>
            <input type="text" name="salePrice" required="required" placeholder="售价" value="${product.salePrice}"/>
            <input type="text" name="inventory" required="required" placeholder="库存数量" value="${product.inventory}"/>
            <input class="button" type="submit" name="submit"/>
            <input class="button" type="reset" name="reset"/>
        </div>
    </form>
</body>
</html>
