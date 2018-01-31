package com.xh.xmlPars;

/**
 * @version ����ʱ�䣺2017-12-20 ����6:33:38 ��Ŀ��repair ������com.xh.paras
 *          �ļ�����ParasFactory.java ���ߣ�lhl ˵��:
 */

public class ParasFactory {
	/**
	 * 
	 * lhl 2017-12-20 ����6:37:48 ˵������ȡdom������
	 * 
	 * @return Paras
	 */
	public static Paras dom() {
		return init(DOMParas.class);
	}

	/**
	 * 
	 * lhl 2017-12-20 ����6:38:07 ˵������ȡpull������
	 * 
	 * @return Paras
	 */
	public static Paras pull() {
		return init(PullParas.class);
	}

	/**
	 * 
	 * lhl 2017-12-20 ����6:38:21 ˵������ȡsax������
	 * 
	 * @return Paras
	 */
	public static Paras sax() {
		return init(SAXParas.class);
	}

	private static Paras init(Class cl) {
		try {
			return (Paras) cl.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
