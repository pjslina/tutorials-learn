package com.panjin.caching.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author panjin
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    String id;

    String description;
}
