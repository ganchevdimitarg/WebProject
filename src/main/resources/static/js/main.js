//nav-bar
$(function() {
    'use strict';

    function toggle(e) {
        if (e) e.preventDefault();

        var $this = $(this),
            $navbar = $this.parents('.navbar'),
            $item = $this.parent();

        $('.nav-item.active', $navbar).removeClass('active');
        $item.addClass('active');

        if ($navbar.hasClass('main-nav')) {
            $('.active', $navbar.siblings('.sub-nav')).removeClass('active');
            $($item.data('target')).addClass('active');
        }
    }

    function leave(e) {
        var $this = $(this),
            $navbar = $this.siblings('.main-nav'),
            $subnav = $('.navbar-nav.active', $this);

        $('[data-target="#' + $subnav.attr('id') + '"]', $navbar).removeClass('hover');
        $subnav.removeClass('active');
    };

    function enter(e) {
        var $this = $(this),
            $navbar = $this.parents('.navbar');

        $('.nav-item.hover', $navbar).removeClass('hover');
        $this.addClass('hover');

        if ($navbar.hasClass('main-nav')) {
            $('.active', $navbar.siblings('.sub-nav')).removeClass('active');
            $($this.data('target')).addClass('active');
        }
    }

    $('.main-nav .nav-link, .sub-nav .nav-link').click(toggle);
    $('.main-nav .nav-item').mouseenter(enter);
    $('.sub-nav').mouseleave(leave);
});

// Contact form
(function ($) {
    "use strict";


    /*==================================================================
    [ Validate ]*/
    var name = $('.validate-input input[name="name"]');
    var email = $('.validate-input input[name="email"]');
    var subject = $('.validate-input input[name="subject"]');
    var message = $('.validate-input textarea[name="message"]');


    $('.validate-form').on('submit',function(){
        var check = true;

        if($(name).val().trim() == ''){
            showValidate(name);
            check=false;
        }

        if($(subject).val().trim() == ''){
            showValidate(subject);
            check=false;
        }


        if($(email).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            showValidate(email);
            check=false;
        }

        if($(message).val().trim() == ''){
            showValidate(message);
            check=false;
        }

        return check;
    });


    $('.validate-form .input1').each(function(){
        $(this).focus(function(){
            hideValidate(this);
        });
    });

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }



})(jQuery);
