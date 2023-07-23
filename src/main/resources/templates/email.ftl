<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Spring boot send mail example</title>
</head>
<body>

<div>
    <img src="${logoURL}" alt="Logo" height="50">

    <p>Hello ${name},</p>

    <p>${message}</p>
    <p>Find attached the PDF document: <a href="${attachPdf}">Download PDF</a></p>
    <p>Best regards,</p>
    <p>${from}</p>
</div>
</body>
</html>