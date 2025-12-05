# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	varx: .word 0
	vary: .word 0
	varignore: .word 0
	
	.text
	.globl __start
__start:
	#block
	#block
	#assignment: x
	#number
	li $v0, 2
	la $t0 varx
	sw $v0 ($t0)
	#assignment: y
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 vary
	sw $v0 ($t0)
	#assignment: x
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	#writeln
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#if
	#condition
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	ble $t0 $v0 endif1
	#block
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#writeln
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
endif1:
	#assignment: x
	#number
	li $v0, 0
	la $t0 varx
	sw $v0 ($t0)
	#while loop
startwhile2:
	#condition
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 10
	lw $t0 ($sp)
	addu $sp $sp 4
	bge $t0 $v0 endwhile2
	#block
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#assignment: x
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	j startwhile2
endwhile2:
	#writeln
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#assignment: ignore
	#procedure call: Add
	subu $sp $sp 4
	sw $ra ($sp)
	#number
	li $v0, 4
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	jal procAdd
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	li $v0, 10
	syscall
	
	#procedure declaration: Add
procAdd:
	subu $sp $sp 4
	sw $zero ($sp)
	#block
	#writeln
	#variable: y
	lw $v0 12($sp)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#writeln
	#variable: w
	lw $v0 8($sp)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#writeln
	#variable: z
	lw $v0 4($sp)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#assignment: x
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	lw $v0 16($sp)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	lw $v0 ($sp)
	addu $sp $sp 4
	jr $ra
	
