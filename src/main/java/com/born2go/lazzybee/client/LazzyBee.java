package com.born2go.lazzybee.client;

import org.timepedia.exporter.client.ExporterUtil;

import com.born2go.lazzybee.client.mainpage.DictionaryTool;
import com.born2go.lazzybee.client.mainpage.EditorTool;
import com.born2go.lazzybee.client.widgets.LoginControl;
import com.born2go.lazzybee.client.widgets.NoticeBox;
import com.born2go.lazzybee.gdatabase.client.rpc.DataService;
import com.born2go.lazzybee.gdatabase.client.rpc.DataServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;

public class LazzyBee implements EntryPoint {

	public static DataServiceAsync data_service = GWT.create(DataService.class);
	public static LoginControl loginControl;

	public static String userId = null;
	public static String userName = null;
	// Google app id and api key
	public static String gClientId = "1090254847247-hhq28qf96obdjm7c7pgr2qo2mt2o842l.apps.googleusercontent.com";
	public static String gApiKey = "AIzaSyCvrB0pS2rE7hUB6N5N6F38Q2TRjvHME7M";
	public static String gScopes = "https://www.googleapis.com/auth/plus.me";
	public static boolean isGoogleInit = false;
	// Facebook app id test lent 1224795884217031
	public static String fClientId = "754889477966743";
	// App notice
	public static NoticeBox noticeBox = new NoticeBox("");

	Anchor menuLogin = new Anchor("Đăng nhập");

	@Override
	public void onModuleLoad() {
		loginControl = new LoginControl();
		menuLogin.setStyleName("header_accPro_item");
		menuLogin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loginControl.center();
			}
		});
		RootPanel.get("menu_login").add(menuLogin);

		if(RootPanel.get("wt_noticebox") != null) {
			RootPanel.get("wt_noticebox").add(noticeBox);
			DOM.getElementById("wt_noticebox").setAttribute("style", "text-align: center;");
			noticeBox.hide();
		}

		if (RootPanel.get("wt_editor_slide") != null) {
			if(DOM.getElementById("wt_editor_slide_dummy") != null)
				DOM.getElementById("wt_editor_slide_dummy").setAttribute("style", "display: none");
			DOM.getElementById("wt_editor_slide").setAttribute("style", "");
			RootPanel.get("wt_editor_slide").add(new EditorTool());
		}
		
		if (RootPanel.get("wt_dictionary_slide") != null) {
			if(DOM.getElementById("wt_dictionary_slide_dummy") != null)
				DOM.getElementById("wt_dictionary_slide_dummy").setAttribute("style", "display: none");
			DOM.getElementById("wt_dictionary_slide").setAttribute("style", "");
			RootPanel.get("wt_dictionary_slide").add(new DictionaryTool());
		}

		ExporterUtil.exportAll();
		onLoadImpl();
	}

	private native void onLoadImpl() /*-{
		if ($wnd.exporterOnLoad && typeof $wnd.exporterOnLoad == 'function') $wnd.exporterOnLoad();
	}-*/;

}
