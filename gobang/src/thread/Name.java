package thread;

public class Name {
	
	public String name(String host) {
		if(host.equals("176.148.23.175"))
			return "���ֺ�";
		if(host.equals("176.148.23.21"))
			return "������"; 
		if(host.equals("176.148.23.22"))
			return "����";
		if(host.equals("176.148.23.243"))
			return "���Ĳ�";
		if(host.equals("176.148.23.244"))
			return "������";
		if(host.equals("176.148.23.245"))
			return "����ޱ";
		if(host.equals("176.148.23.31"))
			return "�¶���";
		if(host.equals("127.0.0.1"))
			return "����";
		if(host.equals("176.148.23.30"))
			return "���м�";
		else return host;
	}
}
