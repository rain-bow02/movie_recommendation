package com.example.shopserve.service.impl;

import com.example.shopserve.dao.MoviesDao;
import com.example.shopserve.dao.RatingDao;
import com.example.shopserve.dao.RecommendDao;
import com.example.shopserve.entity.Movies;
import com.example.shopserve.entity.Rating;
import com.example.shopserve.entity.Recommend;
import com.example.shopserve.result.SimilarityUtil;
import com.example.shopserve.service.MoviesService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (Movies)表服务实现类
 *
 * @author makejava
 * @since 2023-06-14 14:19:28
 */
@Service("moviesService")
public class MoviesServiceImpl implements MoviesService {
    @Resource
    private RecommendDao recommendDao;
    @Resource
    private RatingDao ratingDao;
    @Resource
    private MoviesDao moviesDao;
    //推荐电影
    @Override
    public List<Movies> getRecommendMovies(int userId){
        //得到所有用户的评分
        List <Recommend> recommend=recommendDao.getAllRecommend();
        System.out.println("List <Recommend>"+recommend);
        Map<Integer,Double> similar= new HashMap<>();
        System.out.println("Map<Integer,Double> similar"+similar);
        //转化成以物品为基础的map
        Map<Integer, Map<Integer, Integer>> movieRatings = new HashMap<>();
        movieRatings=GenerateMovieMap(recommend);
        System.out.println("movieRatings"+movieRatings);
        //转化成以用户为基础的map
        Map<Integer, Map<Integer, Integer>> userRatings = new HashMap<>();
        userRatings=GenerateUserMap(recommend);
        System.out.println("userRatings"+userRatings);
        //得到要反馈的用户的电影和评分
        List <Recommend> userList=recommendDao.getRecommendByUserId(userId);
        System.out.println("userList"+userList);
        //计算到所有用户和这各用户的相似度，为了方便又list存的,方法内将查到的用户和相似度做了个拼接
        //list内部：userid_similarsimilar
        List<String> userListSimilarity = SimilarityUtil.getSimilarity(userList, userRatings);
        System.out.println("userListSimilarity"+userListSimilarity);
        //把拼接解了
        for (String s : userListSimilarity) {
            String[] split = s.split(",");
            Integer userId2= Integer.valueOf(split[1]);
            double similarity = Double.parseDouble(split[2]);
            //输出为<userId,similarity>
            similar.put(userId2,(similarity-0.9)*10000);
        }//Map<userid,similarity>
        System.out.println("similar"+similar);
        //Map<userid,similarity>,倒序排序根据相似度
        Map<Integer, Double> similarity=sortByValueDescending(similar);
        System.out.println("similarity"+similarity);
        //取十个用户的电影，对电影进行相似度乘评分的加权评分，返回<movie_id,score>多对多没处理重复值
        List<Recommend> rating =queryByUserId(similarity);
        System.out.println("ratinghh"+rating);
        //评分jiangxu
        Collections.sort(rating, new Comparator<Recommend>() {
            @Override
            public int compare(Recommend u1, Recommend u2) {
                return u1.getScore() - u2.getScore();
            }
        });
        //取一百个评分
        rating=rating.subList(0, 100);
        //处理重复电影并且根据电影的id找到电影返回
        List<Movies> list = getSortedMovie(rating,userList);
        System.out.println("List<Movies> list"+list.get(1));
        return list;
    };
//先将recommend里面的movie score。当遇到map<movie score>
    //map<movie,score>
    //
    public  List<Movies> getSortedMovie(List<Recommend> recommend,List <Recommend> userList){
        List<Movies> movies= new ArrayList<>();
//        List<Movies> recommendMovies= new ArrayList<>();
        new HashMap<>();
        int num=0;
        double totalScore=0;
        Map<Integer, Double> avgScores= recommend.stream()
                .collect(Collectors.groupingBy(Recommend->Recommend.getMovie_id(),
                        Collectors.averagingDouble(Recommend->Recommend.getScore() )
                        )
                );
        for(Recommend r1 : userList){
            if(avgScores.containsKey(r1.getMovie_id())){
                avgScores.remove(r1.getMovie_id());
            }
        }
        avgScores=sortByValueDescending(avgScores);
        for (Map.Entry<Integer,Double> r2 : avgScores.entrySet()) {
            movies.add(moviesDao.queryById(r2.getKey()));
        }
return movies;
    }
    //生成用户画像
    public Map<Integer, Map<Integer, Integer>> GenerateUserMap(List <Recommend> recommend){
        Map<Integer, Map<Integer, Integer>> userRatings = new HashMap<>();
        for (int i = 0; i < recommend.size(); i++) {
            int user_id = recommend.get(i).getUser_id();
            Map<Integer, Integer> movieRatings2 = userRatings.getOrDefault(user_id, new HashMap<>());
            int movieId =  recommend.get(i).getMovie_id();
            int score =  recommend.get(i).getScore();
            movieRatings2.put(movieId, score);
            userRatings.put(user_id, movieRatings2);
        }
        return userRatings;
    }

    //生成电影画像
    public Map<Integer, Map<Integer, Integer>> GenerateMovieMap(List <Recommend> recommend){
        Map<Integer, Map<Integer, Integer>> movieRatings = new HashMap<>();

        for (int i = 0; i < recommend.size(); i++) {
            int movieId = recommend.get(i).getMovie_id();
            Map<Integer, Integer> userRatings = movieRatings.getOrDefault(movieId, new HashMap<>());
            int user_Id =  recommend.get(i).getUser_id();
            int score =  recommend.get(i).getScore();
            userRatings.put(user_Id, score);
            movieRatings.put(movieId, userRatings);
        }
        return movieRatings;
    }

    //value降序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    //用户id和相似度
    // 根据用户id查到movie_id和score
    //List<movie_id,score>  如果movie_id相同，score=avg（score）
    //获得加权score
    public List<Recommend> queryByUserId(Map<Integer,Double> similar) {
        List<Recommend> recommend = new ArrayList<>();
        //键值对
        int limit=10;
        int count=0;
        boolean shouldContinue = true;
        for (Map.Entry<Integer,Double> r2 : similar.entrySet()) {
            count++;
            if (count >= limit) {
                shouldContinue = false;
            }
            if(shouldContinue==false){
                break;
            }
            int num=1;
            double totalscore=0;
            Integer user_id=r2.getKey();
            //根据用户id查到movie_id和score
            List<Recommend> anotherrating=this.recommendDao.getRecommendByUserId(user_id);
            List <Recommend> anotherrating2 = new ArrayList(anotherrating);
            //将找到的score相似度相乘，得到map <movie_id,score>
            for(int i = 0; i<anotherrating2.size(); i++){
                double score = anotherrating2.get(i).getScore();
                score=score*r2.getValue();
                anotherrating2.get(i).setScore((int) score);
                recommend.add(anotherrating2.get(i));
            }


        }return recommend;

    }
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Movies queryById(Integer id) {
        return this.moviesDao.queryById(id);
    }



    @Override
    public List<Movies> showInTypeMovies(String typeId) {
        return this.moviesDao.selectByType(typeId);
    }

    @Override
    public List<Movies> selectAllMovies() {
        return this.moviesDao.queryAllMovies();
    }

    @Override
    public List<Movies> searchMovies(String selectCondition) {
        List<Movies> shopList = this.moviesDao.selectmoviesByCondition(selectCondition);
        return shopList;
    }

//    @Override
//    public List<Movies> getStarredMovies(int userId) {
//        List<Movies> shopList = this.moviesDao.getStarredMovies(userId);
//        return shopList;
//    }
//
//    @Override
//    public List<Movies> getMoviesSeen(int userId) {
//        List<Movies> shopList = this.moviesDao.getMoviesSeen(userId);
//        return shopList;
//    }

}
