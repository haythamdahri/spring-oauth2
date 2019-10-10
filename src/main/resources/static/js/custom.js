// Delete user associated js script
function deleteUser(event)
{
    event.preventDefault();
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '<i class=\"fas fa-check\"></i> Yes, delete it!',
        cancelButtonText: '<i class=\"fas fa-times\"></i> No, cancel'
    }).then((result) => {
        if (result.value) {
            event.target.submit();
        }
    });
}

