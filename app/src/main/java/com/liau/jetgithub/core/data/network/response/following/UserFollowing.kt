import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.liau.jetgithub.core.data.network.response.following.Following
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserFollowing(
    @field:SerializedName("following")
    val following: Following? = null
) : Parcelable