<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<h2>カレンダーURLの取得</h2>

<form action="#" id="wordform">
<div><label for="keywords">キーワード:</label><br><textarea id="keywords" name="keywords" cols="45" rows="5">
</textarea></div>


<button type="submit" class="button positive">
	  		<img src="images/icons/tick.png" alt="作成"> 作成
</button>
</form>
<hr class="space"/>
<div id="result" class="span-9"></div>

<script type="text/javascript">
$('#wordform').submit(function(event) {
    event.preventDefault();
    $('#result').spinner( { position: 'center',
                            img: '${f:url('/images/icons/spinner.gif')}',
                            height : 24,
                            width : 24 } );
    $.post("${f:url('/addcal/')}", $("#wordform").serialize(),
     function(data){
        $('#result').html(data);
         $('#result').spinner('remove');
     }
     );
});
</script>