-- SELECT p.user_id, p.nickname, SUM(pl.like_value) AS like_sum
-- FROM players p,
--      users u,
--      posts po,
--      post_likes pl
-- WHERE p.user_id = u.user_id
--   AND po.author_id = p.user_id
--   AND pl.post_id = po.post_id
-- GROUP BY p.user_id, p.nickname
-- ORDER BY like_sum DESC


SELECT p.user_id, p.nickname, SUM(pl.like_value) AS like_sum
FROM players p,
     users u,
     posts po,
     post_likes pl
WHERE p.user_id = u.user_id
  AND po.author_id = p.user_id
  AND pl.post_id = po.post_id
GROUP BY p.user_id, p.nickname
ORDER BY like_sum DESC
