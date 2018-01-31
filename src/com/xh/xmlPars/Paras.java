package com.xh.xmlPars;

import android.annotation.SuppressLint;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import org.xml.sax.InputSource;
@SuppressLint("NewApi")
public abstract class Paras {
	/**
	 * ���ļ�ת��Ϊlist
	 * 
	 * @param file
	 * @return
	 */
	public List<NodeTree> file2list(File file) {
		return paras(file);
	}

	/**
	 * ���ļ�ת��Ϊnodetree
	 * 
	 * @param file
	 * @return
	 */
	public NodeTree file2nodeTree(File file) {
		List<NodeTree> nodeTrees = file2list(file);
		return nodeTrees == null || nodeTrees.size() == 0 ? null : nodeTrees
				.get(0);
	}

	/**
	 * ��uriת��Ϊlist
	 * 
	 * @param uri
	 * @return
	 */
	public List<NodeTree> uri2list(String uri) {
		return paras(uri);
	}

	/**
	 * ��uriת��Ϊnodetree
	 * 
	 * @param uri
	 * @return
	 */
	public NodeTree uri2nodeTree(String uri) {
		List<NodeTree> nodeTrees = uri2list(uri);
		return nodeTrees == null || nodeTrees.size() == 0 ? null : nodeTrees
				.get(0);
	}

	/**
	 * ��������ת��Ϊlist
	 * 
	 * @param is
	 * @return
	 */
	public List<NodeTree> is2list(InputStream is) {
		return paras(is);
	}

	/**
	 * ��������ת��Ϊnodetree
	 * 
	 * @param is
	 * @return
	 */
	public NodeTree is2nodeTree(InputStream is) {
		List<NodeTree> nodeTrees = is2list(is);
		return nodeTrees == null || nodeTrees.size() == 0 ? null : nodeTrees
				.get(0);
	}

	/**
	 * ���ļ�ת��Ϊlist
	 * 
	 * @param file
	 * @return
	 */
	public abstract List<NodeTree> paras(File file);

	/**
	 * ��uriת��Ϊlist
	 * 
	 * @param uri
	 * @return
	 */
	public abstract List<NodeTree> paras(String uri);
	/**
	 * ��urlת��λlist
	 * xh
	 * 2017-2-12 ����4:03:08
	 * @param url
	 * @return
	 */
	public abstract List<NodeTree> paras(URL url);
    /**
     * ��InputSourceת��λlist
     * xh
     * 2017-2-12 ����4:04:00
     * @param is
     * @return
     */
	public abstract List<NodeTree> paras(InputSource is);
	
	/**
	 * ��isת��Ϊlist
	 * 
	 * @param is
	 * @return
	 */
	public abstract List<NodeTree> paras(InputStream is);

	/**
	 * ��nodetreeת��Ϊstring
	 * 
	 * @param nodeTree
	 * @return
	 */
	public abstract String nodeTree2string(NodeTree nodeTree);

	/**
	 * ��listת��Ϊstring
	 * 
	 * @param nodeTrees
	 * @return
	 */
	public abstract String list2string(List<NodeTree> nodeTrees);
	/**
	 * ����
	 * @param savePath
	 * @param name
	 * @param nodeTree
	 */
	public boolean save(String savePath,String name,NodeTree nodeTree){
		try {
			return save(pathAname2os(savePath, name), nodeTree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * ����
	 * @param savePath
	 * @param name
	 * @param nodeTrees
	 */
	public boolean save(String savePath,String name,List<NodeTree> nodeTrees){
		try {
			return save(pathAname2os(savePath, name), nodeTrees);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * ����
	 * @param file
	 * @param nodeTree
	 */
	public boolean save(File file, NodeTree nodeTree){
		try {
			return save(file2os(file), nodeTree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * ����
	 * @param file
	 * @param nodeTrees
	 */
	public boolean save(File file, List<NodeTree> nodeTrees){
		try {
			return save(file2os(file), nodeTrees);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ����
	 * 
	 * @param path
	 * @param nodeTree
	 */
	public boolean  save(String path, NodeTree nodeTree) {
		try {
			return save(path2os(path), nodeTree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ����
	 * 
	 * @param path
	 * @param nodeTrees
	 */
	public boolean save(String path, List<NodeTree> nodeTrees) {
		try {
			return save(path2os(path), nodeTrees);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ����
	 * 
	 * @param os
	 * @param nodeTree
	 */
	public boolean save(OutputStream os, NodeTree nodeTree) {
		try {
			return save(os, nodeTree2string(nodeTree));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ����
	 * 
	 * @param os
	 * @param nodeTrees
	 */
	public boolean save(OutputStream os, List<NodeTree> nodeTrees) {
		try {
			return save(os, list2string(nodeTrees));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param savePath
	 *            ����Ŀ¼
	 * @param fileName
	 *            �ļ���
	 * @return
	 * @throws Exception
	 */
	public OutputStream pathAname2os(String savePath, String fileName)
			throws Exception {
		if (savePath == null || savePath.isEmpty() || fileName == null
				|| fileName.isEmpty())
			return null;
		File file_path = new File(savePath);
		if (!file_path.exists())
			file_path.mkdirs();
		return path2os(savePath + "/" + fileName);
	}

	/**
	 * ���ļ���ת��Ϊ�����
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public OutputStream path2os(String path) throws Exception {
		if (path == null || path.isEmpty())
			return null;
		return new FileOutputStream(path);
	}

	/**
	 * ���ļ�ת��Ϊ�����
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public OutputStream file2os(File file) throws Exception {
		if (file == null)
			return null;
		return new FileOutputStream(file);
	}

	/**
	 * ��������
	 * 
	 * @param os
	 * @param s
	 * @throws Exception
	 */
	public boolean save(OutputStream os, String s) throws Exception {
		return save(os, s, "UTF-8");
	}

	/**
	 * ��������
	 * 
	 * @param os
	 * @param s
	 * @param charsetName
	 * @throws Exception
	 */
	public boolean save(OutputStream os, String s, String charsetName)
			throws Exception {
		if (s == null)
			return false;
		return save(os, s.getBytes(charsetName));
	}

	/**
	 * ��������
	 * 
	 * @param os
	 * @param buff
	 * @throws Exception
	 */
	public synchronized boolean save(OutputStream os, byte[] buff)
			throws Exception {
		if (os != null) {
			if (buff != null)
				os.write(buff);
			os.flush();
			os.close();
		}
		return true;
	}
	
	NodeTree getNodeTree(List<NodeTree> nodeTrees) {
		if (nodeTrees.size() == 0)
			return null;
		for (int i = 0; i < nodeTrees.size(); i++) {
			NodeTree nodeTree = nodeTrees.get(i);
			if (!nodeTree.isIs_end()) {
				if (nodeTree.hasChildNodeTrees()) {
					NodeTree n = getNodeTree(nodeTree.getChildNodeTrees());
					if (n != null)
						return n;
				}
				return nodeTree;
			}
		}
		return null;
	}
}
