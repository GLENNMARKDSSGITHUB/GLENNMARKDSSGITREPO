/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.repository.image;

import com.dss.entity.image.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface FileStorageRepository which extends the JpaRepository interface
 */

@Repository
public interface FileStorageRepository extends JpaRepository<Images, String> {
}
