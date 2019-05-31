<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <title>Welcome</title>
          <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800" rel="stylesheet" />
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
        <link href="static/black-dashboard-html-v1.0.1/assets/css/black-dashboard.css" rel="stylesheet">
        <link href="static/black-dashboard-html-v1.0.1/assets/css/black-dashboard.min.css" rel="stylesheet">
        <link href="static/black-dashboard-html-v1.0.1/assets/css/black-dashboard.css.map" rel="stylesheet">
       <link href="static/black-dashboard-html-v1.0.1/assets/css/nucleo-icons.css" rel="stylesheet">
       <link href="static/black-dashboard-html-v1.0.1/assets/demo/demo.css" rel="stylesheet">
       
         <link rel="apple-touch-icon" sizes="76x76" href="/static/black-dashboard-html-v1.0.1/assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="static/black-dashboard-html-v1.0.1/assets/img/favicon.png">
<script>
<!-- javascript for init -->
$calendar = $('#fullCalendar');

today = new Date();
y = today.getFullYear();
m = today.getMonth();
d = today.getDate();

$calendar.fullCalendar({
    viewRender: function(view, element) {
        // We make sure that we activate the perfect scrollbar when the view isn't on Month
        if (view.name != 'month'){
            $(element).find('.fc-scroller').perfectScrollbar();
        }
    },
    header: {
left: 'title',
center: 'month,agendaWeek,agendaDay',
right: 'prev,next,today'
},
defaultDate: today,
selectable: true,
selectHelper: true,
    views: {
        month: { // name of view
            titleFormat: 'MMMM YYYY'
            // other view-specific options here
        },
        week: {
            titleFormat: " MMMM D YYYY"
        },
        day: {
            titleFormat: 'D MMM, YYYY'
        }
    },

select: function(start, end) {

        // on select we show the Sweet Alert modal with an input
swal({
    title: 'Create an Event',
    html: '<div class="form-group">' +
                    '<input class="form-control" placeholder="Event Title" id="input-field">' +

                '</div>',
    showCancelButton: true,
            confirmButtonClass: 'btn btn-success',
            cancelButtonClass: 'btn btn-danger',
            buttonsStyling: false
        }).then(function(result) {

            var eventData;
            event_title = $('#input-field').val();

            if (event_title) {
      eventData = {
        title: event_title,
        start: start,
        end: end
      };
      $calendar.fullCalendar('renderEvent', eventData, true); // stick? = true
    }

    $calendar.fullCalendar('unselect');

        });
},
editable: true,
eventLimit: true, // allow "more" link when too many events


    // color classes: [ event-blue | event-azure | event-green | event-orange | event-red ]
    events: [
{
  title: 'All Day Event',
  start: new Date(y, m, 1),
            className: 'event-default'
},
{
  title: 'Meeting',
  start: new Date(y, m, d-1, 10, 30),
  allDay: false,
  className: 'event-green'
},
{
  title: 'Lunch',
  start: new Date(y, m, d+7, 12, 0),
  end: new Date(y, m, d+7, 14, 0),
  allDay: false,
  className: 'event-red'
},
{
  title: 'BD-pro Launch',
  start: new Date(y, m, d-2, 12, 0),
  allDay: true,
  className: 'event-azure'
},
{
  title: 'Birthday Party',
  start: new Date(y, m, d+1, 19, 0),
  end: new Date(y, m, d+1, 22, 30),
  allDay: false,
            className: 'event-azure'
},
{
  title: 'Click for Creative Tim',
  start: new Date(y, m, 21),
  end: new Date(y, m, 22),
  url: 'http://www.creative-tim.com/',
  className: 'event-orange'
},
{
  title: 'Click for Google',
  start: new Date(y, m, 21),
  end: new Date(y, m, 22),
  url: 'http://www.creative-tim.com/',
  className: 'event-orange'
}
]
});
</script>

</head>
<body>
<!-- markup -->
<div class="card">
  <div class="card-body">
    <div id="fullCalendar"></div>
  </div>
</div>
</body>
       <script src="static/black-dashboard-html-v1.0.1/assets/demo/fullcalendar.min.js.download"> </script>
        <script src="static/black-dashboard-html-v1.0.1/assets/demo/fbevents.js.download"> </script>
       <script src="static/black-dashboard-html-v1.0.1/assets/js/core/bootstrap.min.js"> </script>
           <script src="static/black-dashboard-html-v1.0.1/assets/js/core/jquery.min.js"> </script>
           <script src="static/black-dashboard-html-v1.0.1/assets/js/core/popper.min.js"> </script>     
            <script src="static/black-dashboard-html-v1.0.1/assets/js/plugins/perfect-scrollbar.jquery.min.js"> </script>     
            <script src="static/black-dashboard-html-v1.0.1/assets/demo/demo.js"> </script>  
             <script src="static/black-dashboard-html-v1.0.1/assets/js/black-dashboard.min.js"> </script> 
              <script src="static/black-dashboard-html-v1.0.1/assets/js/plugins/chartjs.min.js"> </script>   
               <script src="static/black-dashboard-html-v1.0.1/assets/js/plugins/bootstrap-notify.js"> </script>
                 <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
               <script>
    $(document).ready(function() {
      $().ready(function() {
        $sidebar = $('.sidebar');
        $navbar = $('.navbar');
        $main_panel = $('.main-panel');

        $full_page = $('.full-page');

        $sidebar_responsive = $('body > .navbar-collapse');
        sidebar_mini_active = true;
        white_color = false;

        window_width = $(window).width();

        fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();



        $('.fixed-plugin a').click(function(event) {
          if ($(this).hasClass('switch-trigger')) {
            if (event.stopPropagation) {
              event.stopPropagation();
            } else if (window.event) {
              window.event.cancelBubble = true;
            }
          }
        });

        $('.fixed-plugin .background-color span').click(function() {
          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data', new_color);
          }

          if ($main_panel.length != 0) {
            $main_panel.attr('data', new_color);
          }

          if ($full_page.length != 0) {
            $full_page.attr('filter-color', new_color);
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.attr('data', new_color);
          }
        });

        $('.switch-sidebar-mini input').on("switchChange.bootstrapSwitch", function() {
          var $btn = $(this);

          if (sidebar_mini_active == true) {
            $('body').removeClass('sidebar-mini');
            sidebar_mini_active = false;
            blackDashboard.showSidebarMessage('Sidebar mini deactivated...');
          } else {
            $('body').addClass('sidebar-mini');
            sidebar_mini_active = true;
            blackDashboard.showSidebarMessage('Sidebar mini activated...');
          }

          // we simulate the window Resize so the charts will get updated in realtime.
          var simulateWindowResize = setInterval(function() {
            window.dispatchEvent(new Event('resize'));
          }, 180);

          // we stop the simulation of Window Resize after the animations are completed
          setTimeout(function() {
            clearInterval(simulateWindowResize);
          }, 1000);
        });

        $('.switch-change-color input').on("switchChange.bootstrapSwitch", function() {
          var $btn = $(this);

          if (white_color == true) {

            $('body').addClass('change-background');
            setTimeout(function() {
              $('body').removeClass('change-background');
              $('body').removeClass('white-content');
            }, 900);
            white_color = false;
          } else {

            $('body').addClass('change-background');
            setTimeout(function() {
              $('body').removeClass('change-background');
              $('body').addClass('white-content');
            }, 900);

            white_color = true;
          }


        });

        $('.light-badge').click(function() {
          $('body').addClass('white-content');
        });

        $('.dark-badge').click(function() {
          $('body').removeClass('white-content');
        });
      });
    });
  </script>
  <script>
    $(document).ready(function() {
      // Javascript method's body can be found in assets/js/demos.js
      demo.initDashboardPageCharts();

    });
  </script>            
    <div class="bodyClick"></div>
</html>