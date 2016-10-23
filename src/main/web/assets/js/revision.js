var getRevision = function() {
    $.ajax({
            url: "version",
            success: function(data) {
                $("#build-id").text(data.build);
                $("#rev-id").text(data.revision);
            }
        }
    );
};

getRevision();
