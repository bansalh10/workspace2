package com.nagarro.assignment4.daos;

import java.sql.Blob;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.nagarro.assignment4.data.Constants;
import com.nagarro.assignment4.data.Image;
import com.nagarro.assignment4.data.User;
import com.nagarro.assignment4.services.HibernateUtilities;

/**
 * This class is used to perform operations on Image db.
 * 
 * @author himanshubansal
 *
 */
public class ImageDao {
	/**
	 * This method is used to get images from image table with help of foreign
	 * key userid.
	 * 
	 * @param userid-int
	 * @return images-List
	 */

	public List<Image> getImages(final int userid) {
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		List<Image> images = session
				.createQuery(
						"select image from Image as image,User as user where user.id=:id and user.id=image.userId ")
				.setParameter(Constants.UserId.getName(), userid).getResultList();

		session.getTransaction().commit();
		session.close();
		return images;

	}

	/**
	 * This method is used to delete an image from image table with help of
	 * primary key-imageid.
	 * 
	 * @param imageid
	 */
	public void deleteImage(final int imageid) {
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		session.createQuery("delete  from Image as image where image.imgId=:id")
				.setParameter(Constants.ImageId.getName(), imageid).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * This method is used to get image data of an image with imageid from image
	 * table.
	 * 
	 * @param imageid
	 * @return Blob
	 */
	public Blob getImageData(final int imageid) {
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		Blob imgdata = session.get(Image.class, imageid).getImgData();
		session.getTransaction().commit();
		session.close();
		return imgdata;

	}

	/**
	 * This method is used to update image info in db.
	 * 
	 * @param imgname
	 * @param id
	 */
	public void updateImage(final String imgname, final int id) {
		Session session = HibernateUtilities.getSessionfactory().openSession();
		session.beginTransaction();
		session.createQuery("update  Image as image set image.imgName=:imgname where image.imgId=:id")
				.setParameter(Constants.Updatedname.getName(), imgname).setParameter(Constants.ImageId.getName(), id)
				.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * This saves image in image table if its size is less than 1mb and total
	 * image size of images stored by that user is less than 10mb.Returns trrue
	 * if image is saved.
	 * 
	 * @param user
	 * @param fi
	 * @return boolean
	 */
	public boolean saveImage(final User user, final FileItem fi) {
		boolean validImage = false;
		int imgSize = (int) fi.getSize();
		int tsize = imgSize + user.getTotalsize();
		if (imgSize <= Constants.value_1mb && tsize < (Constants.value_1mb * 10)) {
			byte[] byteImage = fi.get();
			String imgName = fi.getName();
			if (imgName.lastIndexOf("\\") >= 0) {
				imgName = imgName.substring(imgName.lastIndexOf("\\") + 1);
			}

			Session session = HibernateUtilities.getSessionfactory().openSession();
			session.beginTransaction();
			Blob imgData = Hibernate.getLobCreator(session).createBlob(byteImage);
			Image image = new Image(imgData, imgName, imgSize, user);
			user.setTotalsize(tsize);
			session.save(image);
			session.getTransaction().commit();
			session.close();
			validImage = true;
		}
		return validImage;
	}
}
