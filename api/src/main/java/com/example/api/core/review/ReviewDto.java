package com.example.api.core.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class  ReviewDto {
  private int productId;
  private int reviewId;
  private String author;
  private String subject;
  private String content;
}
