package pe.com.master.machines.sqlite.model.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [Index(value = ["email"], unique = true)]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String = "",
    val password: String = "",
)
