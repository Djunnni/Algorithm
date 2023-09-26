SELECT
    CONCAT("/home/grep/src/", F.BOARD_ID, "/", F.FILE_ID, F.FILE_NAME, F.FILE_EXT) as FILE_PATH
FROM
    (
        SELECT
            BOARD_ID
        FROM
            USED_GOODS_BOARD
        WHERE 
            VIEWS = (SELECT MAX(VIEWS) FROM USED_GOODS_BOARD)   
    ) as T
LEFT JOIN 
    USED_GOODS_FILE as F
ON
    T.BOARD_ID = F.BOARD_ID
ORDER BY
    F.FILE_ID DESC