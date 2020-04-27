<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/add.css">
    <title>添加产品</title>
</head>
<body>
    <h3 align="center">添加产品</h3>
    <form  action="ProductServlet" method="post">
        <input type="hidden" name="method" value="add">
        <div id="add">
            <input type="text" name="barcode" required="required" placeholder="商品编码" /><br/>
            <input type="text" name="name" required="required" placeholder="商品名称"/><br/>
            <input type="text" name="units" required="required" placeholder="计量单位"/><br/>
            <input type="text" name="purchasePrice" required="required" placeholder="进价"/><br/>
            <input type="text" name="salePrice" required="required" placeholder="售价" /><br/>
            <input type="text" name="inventory" required="required" placeholder="库存数量"/><br/>
            <input class="button" type="submit" name="submit">
            <input class="button" type="reset" name="reset">
        </div>
    </form>
</body>
</html>