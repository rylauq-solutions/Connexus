console.log("Admin JS loaded");

document
  .querySelector("#image_file_input")
  .addEventListener("change", function (event) {
    var fileName = event.target.files[0];
    var reader = new FileReader();
    var preview = document.getElementById("upload_image_preview");

    reader.onload = function (e) {
      preview.src = reader.result;
      preview.classList.remove("hidden");
    };
    reader.readAsDataURL(fileName);
  });

// Handle reset button to clear image preview
document
  .querySelector('button[type="reset"]')
  .addEventListener("click", function () {
    var preview = document.getElementById("upload_image_preview");
    preview.src = "";
    preview.classList.add("hidden");
  });
