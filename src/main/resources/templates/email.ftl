<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Spring boot send mail example</title>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        body {
            background: aliceblue;
        }
        .backSide {
            background-color: rgb(19, 121, 50);
            width: 100%;
            height: 30vh;
        }
        .logo{
            margin-top: -10%;
        }
    </style>
</head>
<body>

<div>
    <div class="backSide">

    </div>

    <div class="logo">
        <img src="${logoURL}" alt="Logo" height="50">
    </div>

    <div class="content">
        <p>Hello ${name},</p>
        <p>${message}</p>
        <p>Find attached the PDF document: <a href="${attachPdf}">Download PDF</a></p>
        <p>Best regards,</p>
        <p>${from}</p>
    </div>

</div>
</body>
</html>