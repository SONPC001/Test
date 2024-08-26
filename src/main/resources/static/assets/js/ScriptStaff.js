
$(document).ready(function () {
    // Thêm sự kiện cho nút Export
    $('#exportButton').click(function () {
        $.ajax({
            url: 'excel/export/form/staff',
            method: 'GET',
            data: {ajax: true},
            success: function (response) { // Xử lý kết quả trả về
                $('#modalMessage').text(response); // Hiển thị thông báo
                var myModal = new bootstrap.Modal(
                    // Lấy modal theo id
                    document.getElementById('modal')
                );
                myModal.show();
            },
            error: function (xhr) {  // Xử lý lỗi
                $('#modalMessage').text("Export failed");
                var myModal = new bootstrap.Modal(
                    document.getElementById('modal')
                );
                myModal.show();
            }
        });
    });

    // Thêm sự kiện cho nút Import
    $('#importStaffButton').click(function () {
        // Kích hoạt sự kiện click cho input[type=file]
        $('#fileInput').click()
    });

    // Thêm sự kiện cho input[type=file]
    $('#fileInput').change(function () {
        var fileInput = $('#fileInput')[0];
        if (fileInput.files.length > 0) {
            var formData = new FormData();
            formData.append('file', fileInput.files[0]);
            formData.append('ajax', true); // Thêm trường ajax=true vào form data
            $.ajax({
                url: 'excel/import/to/staff',
                method: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                    $('#modalMessage').text(response);
                    var myModal = new bootstrap.Modal(
                        document.getElementById('modal')
                    );
                    myModal.show();
                },
                error: function (xhr) {
                    $('#modalMessage').text("Import failed");
                    var myModal = new bootstrap.Modal(
                        document.getElementById('modal')
                    );
                    myModal.show();
                }
            });
        }
    });
});
