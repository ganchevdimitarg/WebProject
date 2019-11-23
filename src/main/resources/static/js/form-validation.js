// Wait for the DOM to be ready
//TODO
$(function() {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='login-form']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            username: {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 6
            }
        },
        // Specify validation error messages
        messages: {
            username: {
                required: "Please enter your username.",
                minlength: "Your password must be at least 3 characters long",
                maxlength: "Your password must be at maximum 20 characters long"
            },
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 6 characters long"
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler: function(form) {
            form.submit();
        }
    });
});