package com.example.miniprojectakthemmalek.view.adapter;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectakthemmalek.CommentActivity;
import com.example.miniprojectakthemmalek.R;
import com.example.miniprojectakthemmalek.model.entities.Post;
import com.example.miniprojectakthemmalek.model.entities.Tag_post;
import com.example.miniprojectakthemmalek.model.entities.comment;
import com.example.miniprojectakthemmalek.model.entities.like_posts;
import com.example.miniprojectakthemmalek.model.repositories.CommentRepository;
import com.example.miniprojectakthemmalek.model.repositories.ImageRepository;
import com.example.miniprojectakthemmalek.model.repositories.PostRepository;
import com.example.miniprojectakthemmalek.model.repositories.TagPostRepository;
import com.example.miniprojectakthemmalek.model.repositories.UserRepository;
import com.example.miniprojectakthemmalek.model.repositories.like_postsRepository;
import com.example.miniprojectakthemmalek.view.OtherProfileActivity;
import com.example.miniprojectakthemmalek.view.utils.ItemAnimation;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> post_list;

    private String username;
    private ColorDrawable mBackground = new ColorDrawable();
    private int backgroundColor = Color.parseColor("#b80f0a");
    private Paint  mClearPaint = new Paint();
    Context context;


    int x = 0;
    private int animation_type = 0;



    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public PostAdapter(List<Post> post_list, Context context, int animation_type) {
        this.post_list = post_list;
        this.context = context;
        this.animation_type = animation_type;
    }

    public PostAdapter(Context context, int animation_type) {
        this.context = context;
        this.animation_type = animation_type;
    }

    public List<Post> getPost_list() {
        return post_list;
    }

    public void setPost_list(List<Post> post_list) {
        this.post_list = post_list;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.post_item_adapter, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(item);

        return postViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {

        System.out.println(this.post_list.get(position));
        final Post single_post = this.post_list.get(position);

        holder.username_text_view.setText(single_post.getUsername());
        holder.description_text_view.setText(single_post.getDescription());
        holder.geoPosition.setText(single_post.getPosition());


        ImageRepository.getInstance().loadPicutreOf(single_post.getUsername().toString(),0.1f,0.1f, new ImageRepository.getPictureCallBack() {
            @Override
            public void onResponse(Bitmap picUrl) {
                if(picUrl==null)
                {
                    holder.imageView.setImageResource(R.drawable.default_avatar);

                }else{
                    holder.imageView.setImageBitmap(picUrl);
                }
            }
        });
        holder.show_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(), CommentActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("post",single_post.getDescription());
                intent.putExtra("id_post",single_post.getId());
                holder.itemView.getContext().startActivity(intent);

            }
        });

        holder.username_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), OtherProfileActivity.class);
                intent.putExtra("username", holder.username_text_view.getText().toString());
                intent.putExtra("ConnectedUsername", username);
                intent.putExtra("id_post",single_post.getId());
                holder.itemView.getContext().startActivity(intent);

            }
        });
        like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<like_posts> like_posts) {
                holder.likes.setText(like_posts.size() + " Likes");
            }
        });
        CommentRepository.getInstance().getPostComments(single_post.getId(), new CommentRepository.getManyCallback() {
            @Override
            public void getManyOneFollow(List<comment> like_posts) {
                holder.show_comments.setText(like_posts.size()+" Comments");
            }
        });



        TagPostRepository.getInstance().getTagPostById(single_post.getId(), new TagPostRepository.getAllPostCallBack() {
            @Override
            public void onResponse(List<Tag_post> tagPosts) {

                if(tagPosts.size()!=0)
                {

                List<String> list=new ArrayList<String>();
                for(Tag_post tp:tagPosts)
                {
                list.add(tp.getName());
                }

                holder.mTagContainer2.setTags(list);

                }
            }
        });




        holder.like.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View v){
                    if(x==0)

                    {
                like_postsRepository.getInstance().addlike_posts(new like_posts(single_post.getId(), username), new like_postsRepository.addingCallback() {
                    @Override
                    public void addingCallback(int code) {
                        if (code == 200) {
                            x = 1;
                            like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
                                @Override
                                public void getManyOneFollow(List<like_posts> like_posts) {
                                    holder.likes.setText(like_posts.size() + " Likes");
                                    holder.like.setBackgroundResource(R.drawable.seek_thumb_red);
                                }
                            });


                        }
                    }
                });

            }



            if(x==1)


                like_postsRepository.getInstance().dislike(username, single_post.getId(), new like_postsRepository.deletingCallback() {
                    @Override
                    public void deletingCallback(int code) {
                        if (code == 200) {
                            x = 0;

                            like_postsRepository.getInstance().getPostLikes(single_post.getId(), new like_postsRepository.getManyCallback() {
                                @Override
                                public void getManyOneFollow(List<like_posts> like_posts) {
                                    holder.likes.setText(like_posts.size() + " Likes");
                                    holder.like.setBackgroundResource(R.drawable.ic_thumb_up);

                                }
                            });


                        }
                    }
                });


            }


        });
        System.out.println("**********"+x);






        setAnimation(holder.itemView, position);

    }


    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            View itemView = viewHolder.itemView;
            int itemHeight = itemView.getHeight();

            boolean isCancelled = dX == 0 && !isCurrentlyActive;

            if (isCancelled) {
                clearCanvas(c, itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                return;
            }

            mBackground.setColor(backgroundColor);
            mBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
            mBackground.draw(c);

            mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

             Drawable deleteDrawable = ContextCompat.getDrawable(context, R.drawable.ic_delete);

            int intrinsicWidth = deleteDrawable.getIntrinsicWidth();
            int intrinsicHeight = deleteDrawable.getIntrinsicHeight();


            int deleteIconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
            int deleteIconMargin = (itemHeight - intrinsicHeight) / 2;
            int deleteIconLeft = itemView.getRight() - deleteIconMargin - intrinsicWidth;
            int deleteIconRight = itemView.getRight() - deleteIconMargin;
            int deleteIconBottom = deleteIconTop + intrinsicHeight;


            deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
            deleteDrawable.draw(c);

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


        }

        private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
            c.drawRect(left, top, right, bottom, mClearPaint);

        }

        @Override
        public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
            return 0.7f;
        }
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {


           final int position = viewHolder.getAdapterPosition();
            Post single_post= post_list.get(position);

            PostRepository.getInstance().deleteUser(""+single_post.getId(), new PostRepository.addingCallback() {
                @Override
                public void addingCallback(int code) {
                      notifyDataSetChanged();
                      post_list.remove(position);

                }
            });

        }

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {


            int position = viewHolder.getAdapterPosition();
            Post post=post_list.get(position);
            if(post.getUsername().equals(username))
            {

                return makeMovementFlags(1,ItemTouchHelper.START |ItemTouchHelper.END );

            }
            else{

                return makeMovementFlags(0,0 );

            }


        }
    };

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public int getItemCount() {

    return post_list.size();

}



    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }




    public static class PostViewHolder extends RecyclerView.ViewHolder {

        public TextView username_text_view;
        public TextView description_text_view;
        public TextView show_comments;
        public ImageView like;
        public Button likes;
        public  TextView geoPosition;
        CircleImageView imageView;
        public TagContainerLayout mTagContainer2;



        public PostViewHolder(@NonNull View itemView)
        {
            super(itemView);

            username_text_view=itemView.findViewById(R.id.usernamePost);
            description_text_view=itemView.findViewById(R.id.descriptionPost);
            like = itemView.findViewById(R.id.like);
            likes = itemView.findViewById(R.id.likes);
            show_comments=itemView.findViewById(R.id.show_comments);
            imageView=itemView.findViewById(R.id.imageView);
            geoPosition=itemView.findViewById(R.id.geoPosition);
            mTagContainer2=itemView.findViewById(R.id.tag2);
            mTagContainer2.setTheme(ColorFactory.NONE);
            mTagContainer2.setBackgroundColor(Color.TRANSPARENT);

        }



    }

}
