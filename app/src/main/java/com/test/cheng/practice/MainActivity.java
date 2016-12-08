package com.test.cheng.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.test.cheng.practice.model.TestModel;
import com.test.cheng.practice.model.net.ApiLoader;
import com.test.cheng.practice.model.net.ApiManager;
import com.test.cheng.practice.test.Course;
import com.test.cheng.practice.test.Student;
import com.test.cheng.practice.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView tvHelloworld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHelloworld = (TextView) findViewById(R.id.tv_helloworld);
        tvHelloworld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRxJavaHttpGet();
            }
        });
    }

    private void testRxJavaHttpGet() {
        ApiLoader.newApi().testRxJavaHttpGet("1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<TestModel>() {
                    @Override
                    public void call(TestModel testModel) {
                        Logger.d(testModel.toString());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TestModel>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(TestModel testModel) {
                        Logger.d("&&&&&&&&&&&&&&&&&");
                        ToastUtils.show(MainActivity.this, testModel.toString());
                    }
                });
    }

    private void testRetrofitGet() {
        ApiLoader.newApi().testHttpGet("1").enqueue(new Callback<TestModel>() {
            @Override
            public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                Logger.i(response.body().toString());
            }

            @Override
            public void onFailure(Call<TestModel> call, Throwable t) {
            }
        });
    }

    private void testRetrofitPost() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("submit", "1");

        Call<TestModel> test = ApiLoader.newApi().testPost(map);
        test.enqueue(new Callback<TestModel>() {
            @Override
            public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                Log.i("test", "response:" + response.code() + response.body().toString());
            }

            @Override
            public void onFailure(Call<TestModel> call, Throwable t) {
                Logger.i("Throwable:" + t.getMessage());
            }
        });

    }

    private void testRxJava3_2() {
        List<Student> students = new ArrayList<>();
        Course course1 = new Course("语文", "yuwen");
        Course course2 = new Course("英语", "yingyu");
        Course course3 = new Course("数学", "shuxue");
        Course course4 = new Course("思修", "sixiu");
        Course course5 = new Course("政治", "zhengzhi");
        List<Course> list1 = new ArrayList<>();
        List<Course> list2 = new ArrayList<>();
        list1.add(course1);
        list1.add(course2);
        list1.add(course3);
        list2.add(course3);
        list2.add(course4);
        list2.add(course5);
        Student student1 = new Student("studentA", list1);
        Student student2 = new Student("studentB", list2);
        students.add(student1);
        students.add(student2);

//        Action1<List<Course>> action1 = new Action1<List<Course>>() {
//            @Override
//            public void call(List<Course> courses) {
//                for (int i = 0; i < courses.size(); i++){
//                    Logger.d(courses.get(i).getName());
//                }
//            }
//        };
//        Observable.from(students)
//                .map(new Func1<Student, List<Course>>() {
//                    @Override
//                    public List<Course> call(Student student) {
//                        return student.getCoursesList();
//                    }
//                })
//                .subscribe(action1);
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCoursesList());
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Logger.i(course.getName());
                    }
                });
    }

    private void testRxJava3() {
        Observable.just("Hello", "World")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer.intValue() + "";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Logger.d(s);
                    }
                });
    }

    private void testRxJava2() {
//        Observable.just("hello", "world").subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//        });

        Observable.just("hello", "world").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Logger.d(s);
            }
        });
    }

    private void testRxJava() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("onError()");
            }

            @Override
            public void onNext(String s) {
                Logger.d("onNext():" + s);
            }
        };
//        Observable<String> observable1 = Observable.create(new Observable.OnSubscribe<String>() {
//
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("hello");
//                subscriber.onNext("Wrold");
//                subscriber.onCompleted();
//            }
//        });
//        observable1.subscribe(observer);
        Observable observable2 = Observable.just("hello", "world");
        observable2.subscribe(observer);
    }


}
