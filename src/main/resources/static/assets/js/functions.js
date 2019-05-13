var lineNumber = 0;

function addRow() {
    lineNumber++;
    $("#newOrderContainer").append(
        "<div class=\"row\" id=\"orderLine" + lineNumber + "\">" +
            "<div class=\"form-group col-7\">" +
                "<input name=\"productName\" type=\"text\" list=\"products\" class=\"form-control\" placeholder=\"Название товара\" required>" +
                "<div class=\"invalid-feedback\">Введите название товара!" +
                "</div>" +
            "</div>" +
            "<div class=\"form-group col-2\">" +
                "<select name=\"productUoc\" class=\"form-control\">" +
                "<option value=\"шт.\" selected=\"selected\">шт.</option>" +
                "<option value=\"кг\">кг</option>" +
                "<option value=\"л\">л</option>" +
                "<option value=\"г\">г</option>" +
                "<option value=\"м\">м</option>" +
                "</select>" +
            "</div>" +
            "<div class=\"form-group col-2\">" +
                "<input name=\"productAmount\" type=\"text\" class=\"form-control\" required>" +
                "<div class=\"invalid-feedback\">Введите количество!" +
                "</div>" +
            "</div>" +
            "<div class=\"form-group col-1\">" +
                "<button type=\"button\" class=\"btn btn-danger\" onclick=\"deleteRow(" + lineNumber + ");\">&cross;</button>" +
            "</div>" +
        "</div>");
}

function deleteRow(number) {
    $("#orderLine" + number).remove();
}