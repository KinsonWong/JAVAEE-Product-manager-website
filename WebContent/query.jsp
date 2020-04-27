<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/query.css">
    <title>查找产品</title>
</head>
<body>
    <br><br>
    <h3 align="center">查找产品</h3>
    <form  action="ProductServlet" method="get">
        <input type="hidden" name="method" value="query">
        <div id="query">
            <input type="text" name="barcode" placeholder="商品编码" /><br/>
            <input type="text" name="name" placeholder="商品名称"/><br/>
            <input type="text" name="units" placeholder="计量单位"/><br/>
            <input type="text" name="purchasePrice" placeholder="进价"/><br/>
            <input type="text" name="salePrice" placeholder="售价" /><br/>
            <input type="text" name="inventory" placeholder="库存数量"/><br/>
            <input class="button" type="submit" name="submit">
            <input class="button" type="reset" name="reset">
        </div>
    </form>
</body>
</html>
