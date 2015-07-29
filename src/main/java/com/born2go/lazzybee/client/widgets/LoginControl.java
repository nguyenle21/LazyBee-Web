package com.born2go.lazzybee.client.widgets;

import com.born2go.lazzybee.client.LazzyBee;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginControl extends DialogBox {

	private static LoginDialogUiBinder uiBinder = GWT
			.create(LoginDialogUiBinder.class);

	interface LoginDialogUiBinder extends UiBinder<Widget, LoginControl> {
	}

	public LoginControl() {
		setWidget(uiBinder.createAndBindUi(this));
		setAutoHideEnabled(true);
		setGlassEnabled(true);
		setStyleName("LoginControl_clean");
		
		googleInit(LazzyBee.gApiKey);
		facebookInit(LazzyBee.fCLientId);
	}
	
	public void hideDialog() {
		hide();
	}
	
	@Override
	protected void onPreviewNativeEvent(NativePreviewEvent event) {
		super.onPreviewNativeEvent(event);
		switch (event.getTypeInt()) {
		case Event.ONKEYDOWN:
			if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
				hide();
			}
			break;
		}
	}
	
	@UiHandler("googleLogin")
	void onGoogleLoginClick(ClickEvent e) {
		googleLogin(LazzyBee.gClientId, LazzyBee.gScopes, this);
	}
	
	@UiHandler("facebookLogin")
	void onFacebookLoginClick(ClickEvent e) {
		facebookLogin(this);
	}
	
	public static native void checkGoogleLoginStatus(String gClientId, String gScopes) /*-{
		var clientId = gClientId;
		var scopes = gScopes;
        
        function checkAuth() {
        	$wnd.gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
      	}
      	
      	function handleAuthResult(authResult) {
      	}
	}-*/;
	
	public static native void checkFacebookLoginStatus() /*-{
		$wnd.FB.getLoginStatus(function(response) {
			if (response.status === 'connected') {
				var userId = response.authResponse.userID;
			    var accessToken = response.authResponse.accessToken;
			} 
			else if (response.status === 'not_authorized') {} 
			else {}
		}, true);
	}-*/;
	
	native void googleInit(String gApiKey) /*-{
		var apiKey = gApiKey;
		$wnd.gapi.client.setApiKey(apiKey);
	}-*/;
	
	native void googleLogin(String gClientId, String gScopes, LoginControl c) /*-{
		var clientId = gClientId;	
		var scopes = gScopes;		
		
		$wnd.gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthResult);
        return false;
        
        function handleAuthResult(authResult) {
        	if (authResult && !authResult.error) {
          		c.@com.born2go.lazzybee.client.widgets.LoginControl::googleApiCall()();
          		c.@com.born2go.lazzybee.client.widgets.LoginControl::hideDialog()();
        	} else {}
      	}
	}-*/;
	
	native void googleApiCall() /*-{
		$wnd.gapi.client.load('plus', 'v1', function() {
          	var request = $wnd.gapi.client.plus.people.get({
            	'userId': 'me'
          	});
          	request.execute(function(resp) {
          		$wnd.document.getElementById('menu_login').innerHTML = '';
            	var heading = document.createElement('div');
	            var image = document.createElement('img');
	            var span = document.createElement('span');
	            image.src = resp.image.url;
	            image.className = 'header_accPro_img';
	            span.innerHTML = resp.displayName;
	            span.className = 'header_accPro_item';
	            heading.appendChild(image);
	            heading.appendChild(span);
	            $wnd.document.getElementById('menu_login').appendChild(heading);
          	});
       	});
	}-*/;
	
	native void facebookInit(String fClientId) /*-{
		var clientId = fClientId;
	 	$wnd.FB.init({
	      	appId      : clientId,
	      	cookie	 : true,
	      	xfbml      : true,
	      	version    : 'v2.3'
	    });
		
		(function(d, s, id){
		    var js, fjs = d.getElementsByTagName(s)[0];
		    if (d.getElementById(id)) {return;}
		    js = d.createElement(s); js.id = id;
		    js.src = "//connect.facebook.net/en_US/sdk.js";
		    fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	}-*/;
	
	native void facebookLogin(LoginControl c) /*-{
		$wnd.FB.login(function(response) {
			if (response.authResponse) {
				var userId = response.authResponse.userID;
				var accessToken = response.authResponse.accessToken;
					
				c.@com.born2go.lazzybee.client.widgets.LoginControl::facebookApiCall()();
				c.@com.born2go.lazzybee.client.widgets.LoginControl::hideDialog()();
			} else {}
		}, {scope: 'public_profile'});
	}-*/;
	
	native void facebookApiCall() /*-{
		$wnd.FB.api('/me', function(response) {
			$wnd.document.getElementById('menu_login').innerHTML = '';
        	var heading = document.createElement('div');
            var image = document.createElement('img');
            var span = document.createElement('span');
			image.src = "https://graph.facebook.com/"+ response.id + "/picture?width=32&height=32";
			image.className = "header_accPro_img";
            span.innerHTML = response.name;
            span.className = 'header_accPro_item';
            heading.appendChild(image);
            heading.appendChild(span);
            $wnd.document.getElementById('menu_login').appendChild(heading);
		});
	}-*/;
	
}
