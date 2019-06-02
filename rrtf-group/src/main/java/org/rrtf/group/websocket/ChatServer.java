package org.rrtf.group.websocket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket")
@Component
public class ChatServer {
	// 用来存放每个客户端对应的MyWebSocket对象
	private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<ChatServer>();
	private Session session;

	@OnOpen
	public void onopen(Session session) {
		this.session = session;
		webSocketSet.add(this);// 加入set中
		System.out.println("有新连接加入！当前在线人数为" + webSocketSet.size());
		//this.session.getAsyncRemote().sendText("恭喜您成功连接上WebSocket-->当前在线人数为：" + webSocketSet.size());
	}
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		System.out.println("有用户退出群聊，当前人数为："+webSocketSet.size());
	}
	@OnMessage
	public void onMessage(String msg,Session session) {
		boardcast(msg);
	}
	@OnError
	public void onError(Session session,Throwable error) {
		System.out.println(error);
		error.printStackTrace();
	}
	public void boardcast(String msg) {
		for(ChatServer item:webSocketSet) {
			item.session.getAsyncRemote().sendText(msg);
		}
	}
}