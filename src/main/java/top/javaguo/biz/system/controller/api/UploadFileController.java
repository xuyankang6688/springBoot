package top.javaguo.biz.system.controller.api;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import top.javaguo.core.resp.RespBean;
import top.javaguo.utils.GuoRespBeanUtil;

/**
 * 上传文件Controller
 */
@RestController
@RequestMapping("/upload")
public class UploadFileController {
	
	private static String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";// <endpoint, http://oss-cn-hangzhou.aliyuncs.com>
	private static String accessKeyId = "LTAI4Ffke5Py5KxdNVFEB9Uu";// <accessKeyId>
	private static String accessKeySecret = "QnvI82BWRXEo1wd6wsHRmImCVjgwRb";// <accessKeySecret>
	private static String bucketName = "cshongbei";// <bucketName>需要修改  桶名

//	private static String key = "";// <key>
	
	/**
	 * 上传文件
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@PostMapping("/uploadFile")
	public RespBean<Map<String, Object>> handleFileUpload(@RequestParam("file")MultipartFile uploadFile) throws Exception {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
//		long size = uploadFile.getSize();
//			if(size>2*1024*1024) {
//				respBean = GuoRespBeanUtil.setCustomContentRespBean("图片不允许超过2M");
//				return respBean;
//			}
		// 上传
		long startTime = System.currentTimeMillis();// 开始时间
		String key = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"
				+ UUID.randomUUID().toString();// oss文件名（Object Name）
//				+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);// oss文件名（Object Name）
		/*
		 * Constructs a client instance with your account for accessing OSS
		 */
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			// new对象元信息
			ObjectMetadata meta = new ObjectMetadata();
			// 设置contentType
			meta.setContentType(uploadFile.getContentType());
			// 上传文件
			PutObjectResult putObject = ossClient.putObject(bucketName, key, uploadFile.getInputStream(), meta);
		} catch (OSSException oe) {
			System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
		} catch (ClientException ce) {
			System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
		} finally {
			/*
			 * Do not forget to shut down the client finally to release all allocated resources.
			 */
			ossClient.shutdown();
		}
		long endTime = System.currentTimeMillis();// 结束时间
		// 出参
		String baseFilePath = "https://cshongbei.oss-cn-hangzhou.aliyuncs.com/";//前半段修改成桶名
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("uploadFileName", uploadFile.getOriginalFilename());// 文件名称
		respMap.put("uploadFilePath", baseFilePath + key);// 访问路径
		
		respBean.setData(respMap);
		System.err.println("\n上传耗时：" + (endTime - startTime)/1000.0 + "s");
		System.err.println("访问路径：" + respMap.get("uploadFilePath"));
		respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
		return respBean;
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/uploadFileList")
	public RespBean<Map<String, Object>> handleFileUploadList(@RequestParam("files")List<MultipartFile> uploadFiles) throws Exception {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
//		long size = uploadFile.getSize();
//			if(size>2*1024*1024) {
//				respBean = GuoRespBeanUtil.setCustomContentRespBean("图片不允许超过2M");
//				return respBean;
//			}
		// 上传
		long startTime = System.currentTimeMillis();// 开始时间
		String urls = "";
		for (MultipartFile uploadFile : uploadFiles) {
			String key = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"
					+ UUID.randomUUID().toString();// oss文件名（Object Name）
//					+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);// oss文件名（Object Name）
			/*
			 * Constructs a client instance with your account for accessing OSS
			 */
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			try {
				// new对象元信息
				ObjectMetadata meta = new ObjectMetadata();
				// 设置contentType
				meta.setContentType(uploadFile.getContentType());
				// 上传文件
				PutObjectResult putObject = ossClient.putObject(bucketName, key, uploadFile.getInputStream(), meta);
			} catch (OSSException oe) {
				System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
			} catch (ClientException ce) {
				System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
			} finally {
				/*
				 * Do not forget to shut down the client finally to release all allocated resources.
				 */
				ossClient.shutdown();
			}
			long endTime = System.currentTimeMillis();// 结束时间
			// 出参
			String baseFilePath = "https://cshongbei.oss-cn-hangzhou.aliyuncs.com/";//前半段修改成桶名
			Map<String, Object> respMap = new HashMap<String, Object>();
			respMap.put("uploadFileName", uploadFile.getOriginalFilename());// 文件名称
			respMap.put("uploadFilePath", baseFilePath + key);// 访问路径
			urls += baseFilePath+key+",";
			System.err.println("\n上传耗时：" + (endTime - startTime)/1000.0 + "s");
			System.err.println("访问路径：" + respMap.get("uploadFilePath"));
		}
		
		respBean.getData().put("urls", urls);
		respBean=GuoRespBeanUtil.setSuccessRespBean(respBean);
		return respBean;
	}
	
	
	/**
	 * 上传文件
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/uploadFile2")
//	public RespBean<Map<String, Object>> handleFileUpload(@RequestParam("file")MultipartFile uploadFile) throws Exception {
	public RespBean<Map<String, Object>> handleFileUpload2(@RequestParam("file")MultipartFile uploadFile) throws Exception {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		long size = uploadFile.getSize();
			if(size>20*1024*1024) {
				respBean = GuoRespBeanUtil.setCustomContentRespBean("附件不允许超过2M");
				return respBean;
			}
		// 上传
		long startTime = System.currentTimeMillis();// 开始时间
		String key = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"
				+ UUID.randomUUID().toString();// oss文件名（Object Name）
//				+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);// oss文件名（Object Name）
		/*
		 * Constructs a client instance with your account for accessing OSS
		 */
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			// new对象元信息
			ObjectMetadata meta = new ObjectMetadata();
			// 设置contentType
			meta.setContentType(uploadFile.getContentType());
			// 上传文件
			ossClient.putObject(bucketName, key, uploadFile.getInputStream(), meta);
		} catch (OSSException oe) {
			System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
		} catch (ClientException ce) {
			System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
		} finally {
			/*
			 * Do not forget to shut down the client finally to release all allocated resources.
			 */
			ossClient.shutdown();
		}
		long endTime = System.currentTimeMillis();// 结束时间
		// 出参
		String baseFilePath = "https://cshongbei.oss-cn-hangzhou.aliyuncs.com/";//前半段修改成桶名
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("uploadFileName", uploadFile.getOriginalFilename());// 文件名称
		respMap.put("uploadFilePath", baseFilePath + key);// 访问路径
		
		respBean.setData(respMap);
		System.err.println("\n上传耗时：" + (endTime - startTime)/1000.0 + "s");
		System.err.println("访问路径：" + respMap.get("uploadFilePath"));
		respBean=GuoRespBeanUtil.setSuccessRespBean(respBean);
		return respBean;
	}
	
	
	/**支付宝微信二维码图片上传*/
	public static String QrUpload(InputStream in) throws Exception {
		// 上传
		long startTime = System.currentTimeMillis();// 开始时间
		/*String key = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"*/
		String key =  "PaymentQRcode/"
				+ UUID.randomUUID().toString();// oss文件名（Object Name）
//				+ UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);// oss文件名（Object Name）
		/*
		 * Constructs a client instance with your account for accessing OSS
		 */
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			// new对象元信息
			ObjectMetadata meta = new ObjectMetadata();
			// 设置contentType
			meta.setContentType("image/jpg");
			// 上传文件
			ossClient.putObject(bucketName, key, in, meta);
		} catch (OSSException oe) {
			System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
		} catch (ClientException ce) {
			System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
		} finally {
			/*
			 * Do not forget to shut down the client finally to release all allocated resources.
			 */
			ossClient.shutdown();
		}
		long endTime = System.currentTimeMillis();// 结束时间
		// 出参
		String baseFilePath = "https://cshongbei.oss-cn-hangzhou.aliyuncs.com/";//前半段修改成桶名
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("uploadFileName", startTime);// 文件名称
		respMap.put("uploadFilePath", baseFilePath + key);// 访问路径
		System.err.println("\n上传耗时：" + (endTime - startTime)/1000.0 + "s");
		System.err.println("访问路径：" + respMap.get("uploadFilePath"));
		return (String) respMap.get("uploadFilePath");
	}
	
	/**
     * 批量删除object
     */
    @SuppressWarnings("unused")
	public static void deleteBatchObect(List<String> keys) {
        // 删除Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
        OSSClient ossClient = new OSSClient("https://oss-cn-hangzhou.aliyuncs.com", "LTAI4Ffke5Py5KxdNVFEB9Uu", "QnvI82BWRXEo1wd6wsHRmImCVjgwRb ");
        try {
            // 删除Objects
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest( "cshongbei").withKeys(keys));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();// 关闭client
        }
    }
    /**删除object*/
    public static boolean doesObjectExist(String myKey) {
        OSSClient ossClient = new OSSClient("https://oss-cn-hangzhou.aliyuncs.com", "LTAI4Ffke5Py5KxdNVFEB9Uu", "QnvI82BWRXEo1wd6wsHRmImCVjgwRb");
        boolean found = false;
        try {
            found = ossClient.doesObjectExist("cshongbei", myKey);
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();// 关闭client
        }
        return found;
    }
    /**
	 * 指定路径上传文件————盒子标签图片专用
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@PostMapping("/specifiedPathUpload")
	public RespBean<Map<String, Object>> specifiedPathUpload(@RequestParam("file")MultipartFile uploadFile,String name) throws Exception {
		RespBean<Map<String, Object>> respBean = new RespBean<Map<String, Object>>();
		
		// 上传
		long startTime = System.currentTimeMillis();// 开始时间
		String key =  "hezibiaoqianzhuanyongtupian/"+name;
		/*
		 * Constructs a client instance with your account for accessing OSS
		 */
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			// new对象元信息
			ObjectMetadata meta = new ObjectMetadata();
			// 设置contentType
			meta.setContentType(uploadFile.getContentType());
			// 上传文件
			PutObjectResult putObject = ossClient.putObject(bucketName, key, uploadFile.getInputStream(), meta);
		} catch (OSSException oe) {
			System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
		} catch (ClientException ce) {
			System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
		} finally {
			/*
			 * Do not forget to shut down the client finally to release all allocated resources.
			 */
			ossClient.shutdown();
		}
		long endTime = System.currentTimeMillis();// 结束时间
		// 出参
		String baseFilePath = "https://cshongbei.oss-cn-hangzhou.aliyuncs.com/";//前半段修改成桶名
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("uploadFileName", uploadFile.getOriginalFilename());// 文件名称
		respMap.put("uploadFilePath", baseFilePath + key);// 访问路径
		
		respBean.setData(respMap);
		System.err.println("\n上传耗时：" + (endTime - startTime)/1000.0 + "s");
		System.err.println("访问路径：" + respMap.get("uploadFilePath"));
		respBean = GuoRespBeanUtil.setSuccessRespBean(respBean);
		return respBean;
	}

}
