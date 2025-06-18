
function uploadAvatar() {
  // 获取文件输入元素和选中的文件
  const fileInput = document.getElementById("fileInput");
  const file = fileInput.files[0];

  if (!file) {
    alert("请选择一个图片文件");
    return;
  }

  // 将文件转换为 FormData 对象，以构建请求体，Key: "file",Value: 文件对象
  const formData = new FormData();
  formData.append("file", file);

  // 定义请求头、请求体（注意Content-Type并不是JSON）
  fetch('/file/upload/employee/image/oss',{
    method: 'POST',
    body: formData,
    // 不需要设置Content-Type，浏览器会自动设置为multipart/form-data
  }).then(response => response.json())
      .then(data => {
    const imageUrl = data.data;
    document.getElementById("preview").src = imageUrl;
  })
}